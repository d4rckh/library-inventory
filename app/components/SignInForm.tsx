"use client";

import {FormEvent, useState} from "react";
import {signIn} from "@/app/lib/actions/signIn";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";

export default function SignInForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  return <form
    className={"flex flex-col gap-1"}
    onSubmit={(e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    signIn(email, password).then(r => {
      if (r) alert(r);
    });
  }}>
    <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
    <Input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

    <Button type={"submit"}>Log in</Button>
  </form>
}