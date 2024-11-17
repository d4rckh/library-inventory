import {Book} from "@/app/lib/types/Book";
import {Card, CardHeader, CardTitle} from "@/components/ui/card";
import Link from "next/link";

export default function BooksGrid({books}: {
  books: Book[];
}) {
  return <div className={"grid grid-cols-3 mt-3 gap-3"}>
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
}