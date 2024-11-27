import fetchApi from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";

export async function getReservationById(id: number): Promise<Reservation | null> {
    return (await fetchApi<Reservation>("/reservation" + id, ["reservation"])).data ?? null;
}