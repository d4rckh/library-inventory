import fetchApi, {APIResult} from "@/app/lib/fetchApi";

export async function deleteBook(id: number): Promise<APIResult<void>> {
  return (await fetchApi<void>("/book/" + id, ["books"], "DELETE"));
}