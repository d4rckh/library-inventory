import fetchApi from "@/app/lib/fetchApi";

export type ReservationStats = {
    currentReservations: number
}

export async function getReservationStats(): Promise<ReservationStats> {
    return (await fetchApi<ReservationStats>("/reservation/stats", ["reservation"])).data ?? {
        currentReservations: 0
    };
}