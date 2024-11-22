"use client";

import {Book} from "@/app/lib/types/Book";
import {Card, CardHeader, CardTitle} from "@/components/ui/card";
import Link from "next/link";
import {useEffect, useState} from "react";
import {Input} from "@/components/ui/input";
import {getBooks} from "@/app/lib/actions/getBooks";

export default function BooksGridAndSearch({initialBooks}: {
  initialBooks: Book[];
}) {
  const [books, setBooks] = useState<Book[]>(initialBooks);
  const [titleSearchQuery, setTitleSearchQuery] = useState("");

  useEffect(() => {
    getBooks({ titleSearch: titleSearchQuery }).then(setBooks);
  }, [titleSearchQuery])

  return <>
    Title:
    <Input placeholder={"Search by title"} onChange={(e) => setTitleSearchQuery(e.target.value)} />
    <div className={"grid grid-cols-3 mt-3 gap-3"}>
      {
        books.map(book =>
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