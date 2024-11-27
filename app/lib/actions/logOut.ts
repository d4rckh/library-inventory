"use server";

import {cookies} from "next/headers";
import {revalidateTag} from "next/cache";

export async function logOut() {
    const {delete: deleteCookies} = await cookies();
    deleteCookies("auth")
    revalidateTag("auth");
}