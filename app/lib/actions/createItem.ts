"use server";

import {Book} from "@/app/lib/types/Book";
import getApi, {APIResult} from "@/app/lib/fetchApi";
import {revalidateTag} from "next/cache";

export async function createItem(
  bookId: number
): Promise<APIResult<Book>> {
  const r = await getApi<Book>("/inventory", ["inventory"], "POST", {
    bookId
  });
  revalidateTag("books");
  return r;
}