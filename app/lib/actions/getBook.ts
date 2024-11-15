import {Book} from "@/app/lib/types/Book";
import fetchApi from "@/app/lib/fetchApi";

export async function getBook(bookId: number): Promise<Book | null> {
  return (await fetchApi<Book>("/book/" + bookId, ["books"])).data ?? null;
}