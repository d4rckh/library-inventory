import fetchApi from "@/app/lib/fetchApi";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";

export type Reservation = {
  userId: number;
  id: number;
  itemId: number;
  createdAt: string;
  expiresAt: string;
  item: Inventory;
  expiredAt: string;
}

export async function getUserReservations(userId: number): Promise<Reservation[]> {
  return (await fetchApi<Reservation[]>("/reservation/user/" + userId, ["reservation"])).data ?? [];
}