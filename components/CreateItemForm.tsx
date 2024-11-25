"use client";

import {FormEvent} from "react";
import {Button} from "@/components/ui/button";
import {createItem} from "@/app/lib/actions/createItem";
import {useQueryClient} from "@tanstack/react-query";

export default function CreateItemForm({bookId}: { bookId: number }) {
  const query = useQueryClient();

    return <form
    className={"flex mt-2 flex-col gap-1"}
    onSubmit={(e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      createItem(bookId).then(({error}) => {
        if (error) alert(error.message)
        else query.invalidateQueries({ queryKey: [`items-${bookId}`] })
      });
    }}>
    <Button type={"submit"}>
      Create new item
    </Button>
  </form>
}