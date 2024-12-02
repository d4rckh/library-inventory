"use server";

import fetchApi from "@/app/lib/fetchApi";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Book} from "@/app/lib/types/Book";

export type RatingFilters = {
  userId?: number;
  rating?: number;
  bookId?: number;
};

export type Rating = {
  userId: number;
  user: UserInformation;
  rating: number;
  bookId: number;
  book: Book
}

export async function getRatings(filters?: RatingFilters): Promise<Rating[]> {
  let params = "?";
  if (filters) {
    if (filters.userId != undefined) params += "userId=" + filters.userId + "&";
    if (filters != undefined) params += "bookId=" + filters.bookId + "&";
    if (filters.rating != undefined) params += "rating=" + filters.rating + "&";
  }

  return (await fetchApi<Rating[]>("/rating" + params, ["rating"])).data ?? [];
}