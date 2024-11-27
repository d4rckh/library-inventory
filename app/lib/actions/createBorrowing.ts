import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";

export async function createBorrowing(userId: number, itemId: number, borrowDays: number): Promise<APIResult<Borrowing>> {
    return (await fetchApi<Borrowing>("/borrowing", ["borrowing", "reservation"], "POST", {
        userId, itemId,
        borrowDays
    }));
}