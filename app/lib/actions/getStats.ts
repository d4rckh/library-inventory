"use server";

import fetchApi from "@/app/lib/fetchApi";

export type Stats = {
  id: number;
  statDate: string;
  dailyBorrowings: number;
  dailyReturns: number;
  dailyReservations: number;
  dailyUsersRegistered: number;
}

export async function getStats(): Promise<Stats[]> {
  return (await fetchApi<Stats[]>("/stats", ["stats"])).data ?? [];
}