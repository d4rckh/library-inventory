import {Book} from "@/app/lib/types/Book";
import getApi, {APIResult} from "@/app/lib/fetchApi";

export async function createBook(book: Partial<Book>): Promise<APIResult<Book>> {
  return await getApi<Book>("/book", ["books"], "POST", book);
}