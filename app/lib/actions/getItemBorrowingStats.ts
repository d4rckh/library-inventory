import fetchApi, {APIResult} from "@/app/lib/fetchApi";

export type ItemBorrowingStats = {
  times: number;
  borrowed: boolean
}

export async function getItemBorrowingStats(itemId: number): Promise<APIResult<ItemBorrowingStats>> {
  return (await fetchApi<ItemBorrowingStats>("/borrowing/stats/" + itemId, ["borrowing"]));
}