import {Tag} from "@/app/lib/actions/getTags";
import {Badge} from "@/components/ui/badge";
import {X} from "lucide-react";
import React, {useState} from "react";
import {useTags} from "@/lib/queries/items";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {createTag} from "@/app/lib/actions/createTag";
import {useToast} from "@/hooks/use-toast";
import {useQueryClient} from "@tanstack/react-query";

export default function TagsInput({ value, onChange }: { value: Tag[], onChange: (value: Tag[]) => void }) {

  const {data, isSuccess} = useTags();

  const [search, setSearch] = useState("");

  const query = useQueryClient();
  const { toast } = useToast();

  return <>
    <Input placeholder={"Search tag to add ..."} value={search} onChange={(e) => setSearch(e.target.value)} />

    <div className={"flex flex-col gap-1"}>
      { isSuccess && search.length > 0 &&
        data.filter((tag: Tag) => tag.name.toLowerCase().includes(search.toLowerCase()) && !value.map(v => v.id).includes(tag.id)).map((tag) =>
          <Button
            variant={"secondary"}
            size={"sm"} key={tag.id}
                  onClick={() => {
                    onChange([...value, tag])
                    setSearch("");
                  }}
          >Add tag &apos;{tag.name}&apos;</Button>
        )
      }
      {
        search.length > 0 &&
          <Button size={"sm"} onClick={() => {
            createTag(search).then(async (result) => {
              toast({
                title: result.error ? "Error creating tag" : "Successfully created tag",
                description: result.error ? result.error.message : ""
              })

              await query.invalidateQueries({queryKey: ["tags", "list"]});

              if (result.data) onChange([...value, result.data]);

              setSearch("");
            })
          } }>Create & add &apos;{search}&apos; tag</Button>
      }
    </div>

    <div className={"flex flex-row gap-1"}>
      { value.map(tag =>
        <Badge
          onClick={() => {
            onChange(value.filter(a => a.id !== tag.id));
          }}
          key={tag.id}>{tag.name} <X className={"ml-1"}/></Badge>
      ) }
    </div>

  </>

}