"use client"

import {Button} from "@/components/ui/button";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {Trash} from "lucide-react";
import {Dialog, DialogClose, DialogContent, DialogTitle} from "@/components/ui/dialog";
import {DialogTrigger} from "@radix-ui/react-dialog";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {deleteBook} from "@/app/lib/actions/deleteBook";
import {InventoryItem} from "@/app/lib/actions/getItems";
import {deleteItem} from "@/app/lib/actions/deleteItem";

export default function DeleteItemDialog({item}: { item: InventoryItem }) {
  const query = useQueryClient();

  const { toast } = useToast();

  return <Dialog>
    <DialogTrigger asChild>
      <Button variant={"destructive"} size={"icon"}>
        <Trash />
      </Button>
    </DialogTrigger>
    <DialogContent>
      <DialogTitle>
        Delete item?
      </DialogTitle>
      <BookBadgeInformation book={item.book} />
      <DialogClose asChild>
        <Button variant={"destructive"} onClick={() => {
          deleteItem(item.id).then(async r => {
            if (!r.error) {
              await query.invalidateQueries({ queryKey: ["items", "list"] });
            }

            toast({
              title: r.error ? "Error deleting item" : "Successfully deleted item",
              description: r.error ? r.error.message : ""
            });
          })
        }}>Delete item</Button>
      </DialogClose>
    </DialogContent>
  </Dialog>
}