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
  const [publishedDate, setPublishedDate] = useState("");

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
          createBook(title, publisher, author, isbn, [], publishedDate).then(async ({error}) => {
            if (!error) await query.invalidateQueries({queryKey: ["books", "list"]});

            toast({
              title: error ? "Failed to create book" : "Created book successfully",
              description: error ? error.message : "",
            })
          });
        }}>
        <Input placeholder={"Title"} value={title} onChange={(e) => setTitle(e.target.value)}/>
        <Input placeholder={"Publisher"} value={publisher} onChange={(e) => setPublisher(e.target.value)}/>
        <Input placeholder={"Author"} value={author} onChange={(e) => setAuthor(e.target.value)}/>
        <Input placeholder={"ISBN"} value={isbn} onChange={(e) => setIsbn(e.target.value)}/>

        <Input type={"date"} placeholder={"Published Date"} value={publishedDate.split("T")[0]} onChange={(e) => {
          const localDate = new Date(e.target.value); // Converts input to local date
          const utcDate = new Date(localDate.getTime() - localDate.getTimezoneOffset() * 60000); // Adjusts to UTC
          const utcTimestamp = utcDate.toISOString(); // Converts to UTC format with timezone
          setPublishedDate(utcTimestamp);
        }}/>

        <DialogClose asChild>
          <Button type={"submit"}>
            Create book
          </Button>
        </DialogClose>
      </form>
    </DialogContent>
  </Dialog>
}