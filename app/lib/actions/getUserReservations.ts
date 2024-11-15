import {Book} from "@/app/lib/types/Book";
import fetchApi from "@/app/lib/fetchApi";

export type Reservation = {
  userId: number;
  id: number;
  itemId: number;
  createdAt: string;
  expiresAt: string;
  expiredAt: string;
}

export async function getUserReservations({
  userId
                                          }: {
  userId: number;
}): Promise<Reservation[]> {
  return (await fetchApi<Reservation[]>("/reservation/user/" + userId, ["reservation"])).data ?? [];
}