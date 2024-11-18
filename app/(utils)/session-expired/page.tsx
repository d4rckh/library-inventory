"use client";

import {useEffect} from "react";
import {logOut} from "@/app/lib/actions/logOut";
import {redirect} from "next/navigation";

export default function Page() {

  useEffect(() => {
    logOut().then(() => {
      redirect("/account");
    });
  }, []);

  return <>
    Your session expired, you will be redirected to the log in page...
  </>
}