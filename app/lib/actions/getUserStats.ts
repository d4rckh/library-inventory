import fetchApi from "@/app/lib/fetchApi";

export type UserStats = {
  registeredUsers: number;
}

export async function getUserStats(): Promise<UserStats> {
  return (await fetchApi<UserStats>("/user/stats", ["users"])).data ?? {
    registeredUsers: 0,
  };
}