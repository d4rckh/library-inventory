"use client";

import {Button} from "@/components/ui/button";
import {logOut} from "@/app/lib/actions/logOut";

export default function LogOutButton() {
  return <>
    <Button onClick={() => {
      logOut().then();
    }}>Log out</Button>
  </>
}