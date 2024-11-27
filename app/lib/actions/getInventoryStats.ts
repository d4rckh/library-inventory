import fetchApi from "@/app/lib/fetchApi";

export type InventoryStats = {
    uniqueItems: number;
}

export async function getInventoryStats(): Promise<InventoryStats> {
    return (await fetchApi<InventoryStats>("/inventory/stats", ["inventory"])).data ?? {
        uniqueItems: 0,
    };
}