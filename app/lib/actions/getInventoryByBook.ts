import fetchApi from "@/app/lib/fetchApi";
import {Book} from "@/app/lib/types/Book";

export type Inventory = {
    id: number;
    userId: number;
    bookId: number;
    book: Book
}

export async function getInventoryByBook(bookId: number): Promise<Inventory[]> {
    return (await fetchApi<Inventory[]>("/inventory/book/" + bookId, ["inventory"])).data ?? [];
}