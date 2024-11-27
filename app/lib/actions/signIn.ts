"use server";

import getApi, {APIError, APIResult} from "@/app/lib/fetchApi";
import {cookies} from "next/headers";

export type AccessInformation = {
    jwtToken: string
}

export async function signIn(email: string, password: string): Promise<APIError | null> {
    const access: APIResult<AccessInformation> = await getApi("/auth", ["AUTH"], "POST", {
        email, password
    });
    if (access.error != undefined) return access.error;

    const {set} = await cookies();

    if (access.data)
        set("auth", access.data.jwtToken, {
            httpOnly: true,
            maxAge: 1800
        });

    return null;
}