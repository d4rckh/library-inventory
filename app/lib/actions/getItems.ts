"use server";

import fetchApi from "@/app/lib/fetchApi";
import {Book} from "@/app/lib/types/Book";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import {InventoryItemBorrowingStats} from "@/app/lib/actions/getItemBorrowingStats";

export type InventoryFilter = {
  bookId?: number;
};

export type InventoryItem = {
  id: number;
  book: Book;
  user: UserInformation;
  reservation: Reservation;
  borrowing: Borrowing;
  stats: InventoryItemBorrowingStats
}

export async function getItems(filters?: InventoryFilter): Promise<InventoryItem[]> {
  let params = "?";
  if (filters) {
    if (filters.bookId != undefined) params += "bookId=" + filters.bookId + "&";
  }

  return (await fetchApi<InventoryItem[]>("/inventory" + params, ["inventory"])).data ?? [];
}