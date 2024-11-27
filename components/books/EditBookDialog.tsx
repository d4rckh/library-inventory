"use client"

import {Button} from "@/components/ui/button";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {Pencil} from "lucide-react";
import {Book} from "@/app/lib/types/Book";
import {Dialog, DialogClose, DialogContent, DialogTitle} from "@/components/ui/dialog";
import {DialogTrigger} from "@radix-ui/react-dialog";
import {useState} from "react";
import {Input} from "@/components/ui/input";
import {editBook} from "@/app/lib/actions/editBook";

export default function EditBookDialog({book}: { book: Book }) {
  const query = useQueryClient();

  const { toast } = useToast();

  const [newBook, setNewBook] = useState(book);

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"icon"}>
        <Pencil />
      </Button>
    </DialogTrigger>
    <DialogContent>
      <DialogTitle>
        Edit book
      </DialogTitle>
      <b>Title:</b>
      <Input value={newBook.title} onChange={(e) => setNewBook({...newBook, title: e.target.value})}
             placeholder={"Title"}/>
      <b>Author:</b>
      <Input value={newBook.author} onChange={(e) => setNewBook({...newBook, author: e.target.value})}
             placeholder={"Author"}/>
      <b>Publisher:</b>
      <Input value={newBook.publisher} onChange={(e) => setNewBook({...newBook, publisher: e.target.value})}
             placeholder={"Publisher"}/>
      <b>ISBN:</b>
      <Input value={newBook.isbn} onChange={(e) => setNewBook({...newBook, isbn: e.target.value})}
             placeholder={"ISBN"}/>
      <b>Year:</b>
      <Input value={newBook.year} onChange={(e) => setNewBook({...newBook, year: parseInt(e.target.value)})}
             placeholder={"Year"} type={"number"} />
      <DialogClose asChild>
        <Button onClick={() => {
          editBook(newBook).then(async r => {
            if (!r.error) {
              await query.invalidateQueries({queryKey: ["books", "list"]});
              await query.invalidateQueries({queryKey: ["books", "detail", book.id]});
            }

            toast({
              title: r.error ? "Error editing book" : "Successfully edited book",
              description: r.error ? r.error.message : ""
            });
          })
        }}>Edit book</Button>
      </DialogClose>
    </DialogContent>
  </Dialog>
}