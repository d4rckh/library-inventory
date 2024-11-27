"use server";

import fetchApi from "@/app/lib/fetchApi";
import {Book} from "@/app/lib/types/Book";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import {InventoryItemBorrowingStats} from "@/app/lib/actions/getItemBorrowingStats";

export type Stats = {
    id: number;
    statDate: string;
    dailyBorrowings: number;
    dailyReturns: number;
    dailyReservations: number;
}

export async function getStats(): Promise<Stats[]> {
    return (await fetchApi<Stats[]>("/stats", ["stats"])).data ?? [];
}