import {Book} from "@/app/lib/types/Book";
import getApi, {APIResult} from "@/app/lib/fetchApi";

export async function createItem(
  bookId: number
): Promise<APIResult<Book>> {
  return await getApi<Book>("/inventory", ["inventory"], "POST", {
    bookId
  });
}