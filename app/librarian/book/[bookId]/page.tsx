import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import ReservationsDataTable from "@/components/ReservationsDataTable";
import BorrowingsDataTable from "@/components/BorrowingsDataTable";
import {getBookById} from "@/app/lib/actions/getBookById";
import ItemsDataTable from "@/components/ItemsDataTable";

export default async function Page({params}: {
  params: Promise<{ bookId: number }>
}) {
  const { bookId } = await params;
  const book = await getBookById(bookId);

  if (!book) return <>Not found</>;

  return <>
    <Card>
      <CardHeader>
        <CardTitle>{book.title}</CardTitle>
        <CardDescription>Book Management</CardDescription>
      </CardHeader>
      <CardContent>
        <p>Author: {book.author}</p>
        <p>Publisher: {book.publisher}</p>
      </CardContent>
    </Card>

    <Card className={"mt-2"}>
      <CardHeader>
        <CardTitle className={"text-2xl"}>Items</CardTitle>
      </CardHeader>
      <CardContent>
        <ItemsDataTable bookId={book.id} />
      </CardContent>
    </Card>
  </>
}