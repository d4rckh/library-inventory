import getApi from "@/app/lib/fetchApi";

export type UserInformation = {
  email: string,
  id: number,
  firstName: string,
  lastName: string,
  isLibrarian: boolean
}

export async function getLoggedInUser(): Promise<UserInformation | null> {
  return (await getApi<UserInformation>("/auth", ["auth"], "GET", {}, {}, 'no-store')).data ?? null;
}