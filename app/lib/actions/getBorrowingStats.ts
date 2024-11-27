import fetchApi from "@/app/lib/fetchApi";

export type BorrowingStats = {
    currentBorrows: number;
    currentLateBorrows: number
}

export async function getBorrowingStats(): Promise<BorrowingStats> {
    return (await fetchApi<BorrowingStats>("/borrowing/stats", ["borrowing"])).data ?? {
        currentBorrows: 0,
        currentLateBorrows: 0
    };
}