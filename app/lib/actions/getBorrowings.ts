"use server";

import fetchApi from "@/app/lib/fetchApi";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";

export type BorrowingFilters = {
    userId?: number;
    itemId?: number;
    isReturned?: boolean;
};

export async function getBorrowings(filters?: BorrowingFilters): Promise<Borrowing[]> {
    let params = "?";
    if (filters) {
        if (filters.isReturned != undefined) params += "isReturned=" + (filters.isReturned ? 1 : 0) + "&";
        if (filters.itemId != undefined) params += "itemId=" + filters.itemId + "&";
        if (filters.userId != undefined) params += "userId=" + filters.userId + "&";
    }

    return (await fetchApi<Borrowing[]>("/borrowing" + params, ["borrowing"])).data ?? [];
}