import fetchApi from "@/app/lib/fetchApi";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export async function getAllUsers(): Promise<UserInformation[]> {
  return (await fetchApi<UserInformation[]>("/user", ["users"])).data ?? [];
}