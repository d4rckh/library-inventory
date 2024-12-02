"use server";

import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Rating} from "@/app/lib/actions/getRatings";

export async function createRating(rating: Partial<Rating>): Promise<APIResult<Rating>> {
  return (await fetchApi<Rating>("/rating", ["rating"], 'POST', rating));
}