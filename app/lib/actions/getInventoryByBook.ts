import fetchApi from "@/app/lib/fetchApi";

export type Inventory = {
  id: number;
  userId: number;
  bookId: number;
}

export async function getInventoryByBook(bookId: number): Promise<Inventory[]> {
  return (await fetchApi<Inventory[]>("/inventory/book/" + bookId, ["inventory"])).data ?? [];
}