import {getBookById} from "@/app/lib/actions/getBookById";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import ItemsTable from "@/components/inventory/ItemsTable";
import {getItems} from "@/app/lib/actions/getItems";
import RateBookDialog from "@/components/books/RateBookDialog";
import StarRatingSelect from "@/components/StarRatingSelect";

export default async function Page({params}: {
  params: Promise<{ bookId: number }>
}) {

  const {bookId} = await params;

  const items = await getItems({bookId});
  const book = await getBookById(bookId);

  if (!book) return <>Not found</>;

  return <>
    <Card>
      <CardHeader>
        <CardTitle>{book.title}</CardTitle>
      </CardHeader>
      <CardContent>
        <p>Author: {book.author}</p>
        <p>Publisher: {book.publisher}</p>
        <p>ISBN: {book.isbn}</p>
        <p>Year: {book.year}</p>
        <p className={"flex flex-row gap-1"}>Rating: <StarRatingSelect isClickable={false} value={book.rating} /> <RateBookDialog bookId={book.id} /></p>

      </CardContent>
    </Card>
    <Card className={"mt-2"}>
      <CardHeader>
        <CardTitle>All items</CardTitle>
      </CardHeader>
      <ItemsTable items={items} />
    </Card>
  </>;

}