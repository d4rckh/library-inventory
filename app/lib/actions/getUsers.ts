import fetchApi from "@/app/lib/fetchApi";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export type UserFilter = {
  emailSearch: string
}

export async function getUsers(filters?: UserFilter): Promise<UserInformation[]> {
  let params = "?";
  if (filters) {
    if (filters.emailSearch != undefined) params += "email=" + filters.emailSearch + "&";
  }

  return (await fetchApi<UserInformation[]>("/user" + params, ["users"])).data ?? [];
}