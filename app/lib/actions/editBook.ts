import {Book} from "@/app/lib/types/Book";
import getApi, {APIResult} from "@/app/lib/fetchApi";

export async function editBook(book: Book): Promise<APIResult<Book>> {
    return await getApi<Book>("/book/" + book.id, ["books"], "PATCH", book);
}