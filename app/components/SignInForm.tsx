"use client";

import {FormEvent, useState} from "react";
import {signIn} from "@/app/lib/actions/signIn";

export default function SignInForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  return <form onSubmit={(e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    signIn(email, password).then(r => {
      if (r) alert(r);
    });
  }}>
    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

    <input type={"submit"} />
  </form>
}