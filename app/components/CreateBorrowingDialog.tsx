"use client";

import {
  Dialog,
  DialogHeader,
  DialogContent,
  DialogDescription,
  DialogTitle,
  DialogTrigger,
  DialogClose
} from "@/components/ui/dialog";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Badge} from "@/components/ui/badge";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {useState} from "react";
import {createBorrowing} from "@/app/lib/actions/createBorrowing";

export default function CreateBorrowingDialog({
  user, item
                                              }: {
  user: UserInformation, item: Inventory
}) {
  const [days, setDays] = useState(14);

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"sm"} variant={"secondary"}>Register Borrow</Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Create a new borrow</DialogTitle>
        <DialogDescription>

        </DialogDescription>
      </DialogHeader>
      <span>User: <Badge>{user.email} (ID: {user.id})</Badge></span>
      <span>Book: <b>{item.book.title}</b> by <i>{item.book.author}</i> (Publisher: {item.book.publisher})</span>
      <span>Return in how many days?</span>
      <Input
        type={"number"}
        value={days}
        onChange={(e) => setDays(parseInt(e.target.value))}
        placeholder={"Duration of the borrow in days"}
      />
      <DialogClose asChild>
        <Button
          onClick={() => {
            createBorrowing(user.id, item.id, days).then(r => {
              if (r.data) alert("Successfully created borrowing");
              else if (r.error) alert(r.error.message);
            });
          }}
        >
          Register
        </Button>
      </DialogClose>
    </DialogContent>
  </Dialog>;
}