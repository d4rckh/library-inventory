import {Book} from "@/app/lib/types/Book";
import fetchApi from "@/app/lib/fetchApi";

export async function getAllBooks(): Promise<Book[]> {
  return (await fetchApi<Book[]>("/book", ["books"])).data ?? [];
}