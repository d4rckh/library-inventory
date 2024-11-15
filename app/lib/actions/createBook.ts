import {Book} from "@/app/lib/types/Book";
import getApi, {APIResult} from "@/app/lib/fetchApi";

export async function createBook(
    title: string,
    publisher: string,
    author: string,
    isbn: string,
    tags: string[],
    publishedDate: string,
): Promise<APIResult<Book>> {
  return await getApi<Book>("/book", ["books"], "POST", {
    title, publisher, author, isbn, tags, publishedDate
  });
}