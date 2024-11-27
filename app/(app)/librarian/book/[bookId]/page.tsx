import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import {getBookById} from "@/app/lib/actions/getBookById";
import ItemsDataTable from "@/components/inventory/ItemsDataTable";
import CreateItemForm from "@/components/inventory/CreateItemForm";
import CellActions from "@/components/CellActions";
import EditBookDialog from "@/components/books/EditBookDialog";
import DeleteBookDialog from "@/components/books/DeleteBookDialog";

export default async function Page({params}: {
    params: Promise<{ bookId: number }>
}) {
    const {bookId} = await params;
    const book = await getBookById(bookId);

    if (!book) return <>Not found</>;

    return <>
        <Card>
            <CardHeader>
                <CardTitle>{book.title}</CardTitle>
                <CardDescription>Book Management</CardDescription>
            </CardHeader>
            <CardContent>
                <div className={"mb-4"}>
                    <CellActions>
                        <EditBookDialog book={book} />
                        <DeleteBookDialog book={book} />
                    </CellActions>
                </div>
                <p>Author: {book.author}</p>
                <p>Publisher: {book.publisher}</p>
            </CardContent>
        </Card>
        <Card className={"mt-2"}>
            <CardHeader>
                <CardTitle className={"text-2xl"}>Items</CardTitle>
            </CardHeader>
            <CardContent>
                <CreateItemForm bookId={book.id}/>
                <ItemsDataTable bookId={book.id}/>
            </CardContent>
        </Card>
    </>
}