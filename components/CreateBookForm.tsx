"use client";

import {FormEvent, useState} from "react";
import {createBook} from "@/app/lib/actions/createBook";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";

export default function CreateBookForm() {
  const [title, setTitle] = useState("");
  const [publisher, setPublisher] = useState("");
  const [author, setAuthor] = useState("");
  const [isbn, setIsbn] = useState("");
  const [publishedDate, setPublishedDate] = useState("");

  return <form
    className={"flex flex-col gap-1"}
    onSubmit={(e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      createBook(title, publisher, author, isbn, [], publishedDate).then(({error}) => {
        if (error) alert(error.message)
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

    <Button type={"submit"}>
      Create book
    </Button>
  </form>
}