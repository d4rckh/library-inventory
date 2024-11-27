import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";

export async function markBorrowingAsReturned(borrowingId: number): Promise<APIResult<Borrowing>> {
    return (await fetchApi<Borrowing>("/borrowing/" + borrowingId + "/returned", ["borrowing"], 'POST'));
}