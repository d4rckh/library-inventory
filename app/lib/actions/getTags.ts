"use server";

import fetchApi from "@/app/lib/fetchApi";

// export type ReservationFilters = {
//   isActive?: boolean,
//   userId?: number,
//   itemId?: number
// }

export type Tag = {
    id: number,
    name: string,
}

export async function getTags(): Promise<Tag[]> {
    // let params = "?";
    // if (filters) {
    //   if (filters.isActive != undefined) params += "isActive=" + (filters.isActive ? 1 : 0) + "&";
    //   if (filters.itemId != undefined) params += "itemId=" + filters.itemId + "&";
    //   if (filters.userId != undefined) params += "userId=" + filters.userId + "&";
    // }
    return (await fetchApi<Tag[]>("/tag", ["tags"])).data ?? [];
}