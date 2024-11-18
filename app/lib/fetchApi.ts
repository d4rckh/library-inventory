"use server";

import {cookies} from "next/headers";
import {revalidateTag} from "next/cache";
import {redirect} from "next/navigation";

export type APIError = {
  status: string,
  message: string,
  timestamp: string
};

export type APIResult<D> = {
  data?: D,
  error?: APIError
}

export default async function fetchApi<D>(
  path: string,
  tags: string[] = [],
  method: 'GET' | 'POST' | 'PUT' | 'DELETE' = "GET",
  body: object = {},
  cacheOptions: NextFetchRequestConfig = {},
  requestCache: RequestCache = 'force-cache',
): Promise<APIResult<D>> {
  const {get} = await cookies();
  const authToken = get("auth")?.value;

  const result = await fetch(process.env.BACKEND as string + path, {
    method: method,
    next: {
      tags,
      ...cacheOptions,
    },
    cache: requestCache,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + authToken,
    },
    body: method == "GET" ? null : JSON.stringify(body),
  })

  if (result.status.toString().startsWith("2")) {
    if (method != "GET") {
      tags.forEach(revalidateTag);
    }

    return {
      error: undefined,
      data: await result.json() as D
    }
  } else
    if (method == "GET" && path == "/auth" && authToken != undefined) {
      console.log(method, path, authToken, result.status);
      redirect("/session-expired");
    }
    return {
      error: await result.json(),
      data: undefined
    }
}