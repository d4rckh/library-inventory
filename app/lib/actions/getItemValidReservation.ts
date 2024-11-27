import fetchApi from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";

export async function getItemValidReservation(itemId: number): Promise<Reservation | null> {
  return (await fetchApi<Reservation>("/reservation/item/" + itemId + "/valid", ["reservation"], 'GET', {
    itemId,
  })).data ?? null;
}