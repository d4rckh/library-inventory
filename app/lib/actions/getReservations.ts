"use server";

import fetchApi from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";

export type ReservationFilters = {
    isActive?: boolean,
    userId?: number,
    itemId?: number
}

export async function getReservations(filters?: ReservationFilters): Promise<Reservation[]> {
    let params = "?";
    if (filters) {
        if (filters.isActive != undefined) params += "isActive=" + (filters.isActive ? 1 : 0) + "&";
        if (filters.itemId != undefined) params += "itemId=" + filters.itemId + "&";
        if (filters.userId != undefined) params += "userId=" + filters.userId + "&";
    }
    return (await fetchApi<Reservation[]>("/reservation" + params, ["reservation"])).data ?? [];
}