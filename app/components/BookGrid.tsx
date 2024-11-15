import {Book} from "@/app/lib/types/Book";
import {Card, CardHeader, CardTitle} from "@/components/ui/card";

export default function BookGrid({ books }: {
    books: Book[];
}) {
    return <div className={"grid grid-cols-3 mt-3 gap-3"}>
        {
            books.map(book =>
                <Card key={book.id}>
                    <CardHeader>
                        <CardTitle>{book.title}</CardTitle>
                        <span>by <i>{book.author}</i></span>
                    </CardHeader>
                </Card>
            )
        }
    </div>
}