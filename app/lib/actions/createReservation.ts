import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";

export async function createReservation(itemId: number): Promise<APIResult<Reservation>> {
  return (await fetchApi<Reservation>("/reservation", ["reservation"], 'POST', {
    itemId,
  }));
}