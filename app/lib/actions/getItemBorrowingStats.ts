import fetchApi from "@/app/lib/fetchApi";

export type InventoryItemBorrowingStats = {
    times: number;
    borrowed: boolean
}

export async function getItemBorrowingStats(itemId: number): Promise<InventoryItemBorrowingStats> {
    return (await fetchApi<InventoryItemBorrowingStats>("/borrowing/stats/" + itemId, ["borrowing"])).data ?? {
        times: 0,
        borrowed: false
    };
}