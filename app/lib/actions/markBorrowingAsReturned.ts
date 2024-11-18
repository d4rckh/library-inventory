import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";

export async function markBorrowingAsReturned(borrowingId: number): Promise<APIResult<Borrowing>> {
  return (await fetchApi<Borrowing>("/borrowing/" + borrowingId + "/returned", ["borrowing"], 'POST'));
}