import fetchApi from "@/app/lib/fetchApi";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export type Borrowing = {
    userId: number;
    id: number;
    itemId: number;
    item: Inventory;
    user: UserInformation;
    borrowDate: string;
    returnDate: string;
    returnedDate: string;
}

export async function getUserBorrowings(userId: number): Promise<Borrowing[]> {
    return (await fetchApi<Borrowing[]>("/borrowing/user/" + userId, ["borrowing"])).data ?? [];
}