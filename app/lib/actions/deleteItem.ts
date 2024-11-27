import fetchApi, {APIResult} from "@/app/lib/fetchApi";

export async function deleteItem(id: number): Promise<APIResult<void>> {
    return (await fetchApi<void>("/inventory/" + id, ["inventory"], "DELETE"));
}