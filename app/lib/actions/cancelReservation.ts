import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Reservation} from "@/app/lib/actions/getUserReservations";

export async function cancelReservation(reservationId: number): Promise<APIResult<Reservation>> {
  return (await fetchApi<Reservation>("/reservation/" + reservationId + "/cancel", ["reservation"], 'POST'));
}