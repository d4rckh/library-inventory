"use server";

import fetchApi from "@/app/lib/fetchApi";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";

export type InventoryFilter = {
  bookId?: number;
};

export async function getItems(filters?: InventoryFilter): Promise<Inventory[]> {
  let params = "?";
  if (filters) {
    if (filters.bookId != undefined) params += "bookId=" + filters.bookId + "&";
  }

  return (await fetchApi<Inventory[]>("/inventory" + params, ["inventory"])).data ?? [];
}