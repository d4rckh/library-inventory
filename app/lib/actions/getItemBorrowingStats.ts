import fetchApi, {APIResult} from "@/app/lib/fetchApi";

export type BorrowingStats = {
  times: number;
  borrowed: boolean
}

export async function getItemBorrowingStats(itemId: number): Promise<APIResult<BorrowingStats>> {
  return (await fetchApi<BorrowingStats>("/borrowing/stats/" + itemId, ["inventory"]));
}