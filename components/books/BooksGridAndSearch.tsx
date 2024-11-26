"use client";

import {Card, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import Link from "next/link";
import {useState} from "react";
import {Input} from "@/components/ui/input";
import {useBooks} from "@/lib/queries/items";
import {Badge} from "@/components/ui/badge";

export default function BooksGridAndSearch() {
  const [titleSearch, setTitleSearch] = useState("");

  const { data, isPending, isSuccess, isError } = useBooks({ titleSearch });

  return <>
    <b>Title:</b>
    <Input placeholder={"Search by title"} onChange={(e) => setTitleSearch(e.target.value)} />
    <div className={"grid grid-cols-3 mt-3 gap-3"}>
      { isPending && "Loading..." }
      { isError && "Failed to fetch books" }
      {
        isSuccess && data.map(book =>
            <Link key={book.id} href={`/book/${book.id}`}>
              <Card>
                <CardHeader>
                  <CardTitle>{book.title}</CardTitle>
                  <CardDescription>
                    <span>by <i>{book.author}</i></span>
                    { book.tags.map(tag => <Badge key={tag.id}>{tag.name}</Badge> ) }
                  </CardDescription>
                </CardHeader>
              </Card>
            </Link>
        )
      }
    </div>
  </>
}