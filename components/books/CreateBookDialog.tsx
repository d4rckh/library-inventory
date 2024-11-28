"use client";

import {FormEvent, useState} from "react";
import {createBook} from "@/app/lib/actions/createBook";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {Dialog, DialogClose, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import {BookPlus} from "lucide-react";

export default function CreateBookDialog() {
  const [title, setTitle] = useState("");
  const [publisher, setPublisher] = useState("");
  const [author, setAuthor] = useState("");
  const [isbn, setIsbn] = useState("");
  const [year, setYear] = useState(2000);

  const query = useQueryClient();
  const { toast } = useToast();

  return <Dialog>
    <DialogTrigger asChild>
      <Button variant={"outline"}><BookPlus /> Create a new book</Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Create a new book</DialogTitle>
      </DialogHeader>
      <form
          className={"flex flex-col gap-1"}
          onSubmit={(e: FormEvent<HTMLFormElement>) => {
            e.preventDefault();
            createBook({title, publisher, author, isbn, tags: [], year}).then(async ({error}) => {
              if (!error) await query.invalidateQueries({queryKey: ["books", "list"]});

              toast({
                title: error ? "Failed to create book" : "Created book successfully",
                description: error ? error.message : "",
              })
            });
          }}>
        <b>Title:</b>
        <Input placeholder={"Title"} value={title} onChange={(e) => setTitle(e.target.value)}/>
        <b>Publisher:</b>
        <Input placeholder={"Publisher"} value={publisher} onChange={(e) => setPublisher(e.target.value)}/>
        <b>Author:</b>
        <Input placeholder={"Author"} value={author} onChange={(e) => setAuthor(e.target.value)}/>
        <b>ISBN:</b>
        <Input placeholder={"ISBN"} value={isbn} onChange={(e) => setIsbn(e.target.value)}/>
        <b>Year:</b>
        <Input placeholder={"Year"} value={year} onChange={(e) => setYear(parseInt(e.target.value))} type={"number"}/>

        <DialogClose asChild>
          <Button type={"submit"}>
            Create book
          </Button>
        </DialogClose>
      </form>
    </DialogContent>
  </Dialog>
}