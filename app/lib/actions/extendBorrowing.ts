import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";

export async function extendBorrowing(borrowingId: number, extendDays: number): Promise<APIResult<Borrowing>> {
  return (await fetchApi<Borrowing>("/borrowing/extend", ["borrowing"], 'POST', {
    borrowingId, extendDays
  }));
}