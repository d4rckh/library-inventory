"use client";

import {FormEvent} from "react";
import {Button} from "@/components/ui/button";
import {createItem} from "@/app/lib/actions/createItem";

export default function CreateItemForm({bookId}: { bookId: number }) {
  return <form
    className={"flex mt-2 flex-col gap-1"}
    onSubmit={(e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      createItem(bookId).then(({error}) => {
        if (error) alert(error.message)
      });
    }}>
    <Button type={"submit"}>
      Create new item
    </Button>
  </form>
}