import fetchApi from "@/app/lib/fetchApi";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export type Reservation = {
    userId: number;
    id: number;
    itemId: number;
    createdAt: string;
    expiresAt: string;
    item: Inventory;
    user: UserInformation;
    expiredAt: string;
    cancelled: boolean
}

export async function getUserReservations(userId: number): Promise<Reservation[]> {
    return (await fetchApi<Reservation[]>("/reservation/user/" + userId, ["reservation"])).data ?? [];
}