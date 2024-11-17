import fetchApi from "@/app/lib/fetchApi";

export type ItemBorrowingStats = {
  times: number;
  borrowed: boolean
}

export async function getItemBorrowingStats(itemId: number): Promise<ItemBorrowingStats> {
  return (await fetchApi<ItemBorrowingStats>("/borrowing/stats/" + itemId, ["borrowing"])).data ?? {
    times: 0,
    borrowed: false
  };
}