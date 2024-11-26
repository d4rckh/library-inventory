"use client"

import {Button} from "@/components/ui/button";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {Trash} from "lucide-react";
import {Book} from "@/app/lib/types/Book";
import {Dialog, DialogClose, DialogContent, DialogTitle} from "@/components/ui/dialog";
import {DialogTrigger} from "@radix-ui/react-dialog";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {deleteBook} from "@/app/lib/actions/deleteBook";

export default function DeleteBookDialog({book}: { book: Book }) {
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
        Delete book?
      </DialogTitle>
      <BookBadgeInformation book={book} />
      <DialogClose asChild>
        <Button variant={"destructive"} onClick={() => {
          deleteBook(book.id).then(async r => {
            if (!r.error) {
              await query.invalidateQueries({ queryKey: ["books", "list"] });
            }

            toast({
              title: r.error ? "Error deleting book" : "Successfully deleted book",
              description: r.error ? r.error.message : ""
            });
          })
        }}>Delete book</Button>
      </DialogClose>
    </DialogContent>
  </Dialog>
}