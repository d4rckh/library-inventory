import fetchApi from "@/app/lib/fetchApi";

export type BookStats = {
    uniqueTitles: number;
}

export async function getBookStats(): Promise<BookStats> {
    return (await fetchApi<BookStats>("/book/stats", ["books"])).data ?? {
        uniqueTitles: 0,
    };
}