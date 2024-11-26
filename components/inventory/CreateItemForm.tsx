"use client";

import {FormEvent} from "react";
import {Button} from "@/components/ui/button";
import {createItem} from "@/app/lib/actions/createItem";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";

export default function CreateItemForm({bookId}: { bookId: number }) {
  const query = useQueryClient();
  const { toast } = useToast();

  return <form
    className={"flex mt-2 flex-col gap-1"}
    onSubmit={(e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      createItem(bookId).then(async ({error}) => {
        if (!error) await query.invalidateQueries({ queryKey: ["items", "list"] });

        toast({
          title: error ? "Error creating a new item for this book" : "Created a new item for this book",
          description: error ? error.message : "",
        })
      });
    }}>
    <Button type={"submit"}>
      Create new item
    </Button>
  </form>
}