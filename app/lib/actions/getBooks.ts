import {Book} from "@/app/lib/types/Book";
import fetchApi from "@/app/lib/fetchApi";

export type BookFilters = {
  titleSearch?: string
}

export async function getBooks(filters?: BookFilters): Promise<Book[]> {
  let params = "?";
  if (filters) {
    if (filters.titleSearch != undefined) params += "titleSearch=" + filters.titleSearch + "&";
  }

  return (await fetchApi<Book[]>("/book" + params, ["books"])).data ?? [];
}