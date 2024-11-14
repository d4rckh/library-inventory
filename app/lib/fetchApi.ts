import {cookies} from "next/headers";

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
): Promise<APIResult<D>> {
  const { get } = await cookies();
  const authToken = get("auth")?.value;

  const result = await fetch(process.env.BACKEND as string + path, {
    method: method,
    next: {
      tags
    },
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + authToken,
    },
    body: method == "GET" ? null : JSON.stringify(body),
  })

  if (result.status.toString().startsWith("2"))
    return {
      error: undefined,
      data: await result.json() as D
    }
  else
    return {
      error: await result.json(),
      data: undefined
    }
}