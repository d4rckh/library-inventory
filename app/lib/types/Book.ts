import {Tag} from "@/app/lib/actions/getTags";

export type Book = {
    title: string,
    isbn: string,
    author: string,
    publisher: string,
    publishedAt: string,
    tags: Tag[],
    id: number,
    userId: number,

    totalItems: number
}