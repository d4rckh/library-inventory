import getApi, {APIError, APIResult} from "@/app/lib/fetchApi";
import {cookies} from "next/headers";

export type UserInformation = {
  email: string,
  id: number
}

export async function getUser(): Promise<UserInformation | null> {
  return (await getApi<UserInformation>("/auth", ["AUTH"], "GET")).data ?? null;
}