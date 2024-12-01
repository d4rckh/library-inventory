"use server";

import fetchApi, {APIResult} from "@/app/lib/fetchApi";
import {Tag} from "@/app/lib/actions/getTags";

export async function createTag(name: string): Promise<APIResult<Tag>> {
  return (await fetchApi<Tag>("/tag", ["tags"], 'POST', {
    name
  }));
}