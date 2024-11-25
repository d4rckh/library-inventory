"use client";

import {Book} from "@/app/lib/types/Book";
import {Card, CardHeader, CardTitle} from "@/components/ui/card";
import Link from "next/link";
import {useState} from "react";
import {Input} from "@/components/ui/input";
import {getBooks} from "@/app/lib/actions/getBooks";
import {useQuery} from "@tanstack/react-query";

export default function BooksGridAndSearch({initialBooks}: {
  initialBooks: Book[];
}) {
  const [titleSearch, setTitleSearch] = useState("");

  const { data, isPending } = useQuery({
    queryKey: [ "books", { titleSearch } ],
    queryFn: () => getBooks({ titleSearch }),
    placeholderData: initialBooks
  })

  return <>
    <b>Title:</b>
    <Input placeholder={"Search by title"} onChange={(e) => setTitleSearch(e.target.value)} />
    <div className={"grid grid-cols-3 mt-3 gap-3"}>
      { isPending && "Loading..." }
      {
        data && data.map(book =>
            <Link key={book.id} href={`/book/${book.id}`}>
              <Card>
                <CardHeader>
                  <CardTitle>{book.title}</CardTitle>
                  <span>by <i>{book.author}</i></span>
                </CardHeader>
              </Card>
            </Link>
        )
      }
    </div>
  </>
}