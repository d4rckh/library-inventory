import fetchApi from "@/app/lib/fetchApi";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export async function getUserById(id: number): Promise<UserInformation | undefined> {
    return (await fetchApi<UserInformation>("/user/" + id, ["users"])).data;
}