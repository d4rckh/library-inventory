import {getInventoryByBook} from "@/app/lib/actions/getInventoryByBook";
import {getBook} from "@/app/lib/actions/getBook";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import ItemsGrid from "@/app/components/ItemsGrid";

export default async function Page({ params }: {
    params: Promise<{ bookId: number }>
}) {

    const { bookId } = await params;

    const items = await getInventoryByBook(bookId);
    const book = await getBook(bookId);

    if (!book) return <>Not found</>;

    return <>
        <Card>
            <CardHeader>
                <CardTitle>{book.title}</CardTitle>
            </CardHeader>
            <CardContent>
                <p>Author: {book.author}</p>
            </CardContent>
        </Card>
        <ItemsGrid items={items} />
    </>

}