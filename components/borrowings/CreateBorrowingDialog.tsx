"use client";

import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from "@/components/ui/dialog";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {useState} from "react";
import {createBorrowing} from "@/app/lib/actions/createBorrowing";
import UserSelectorFinder from "@/components/users/UserSelectorFinder";
import {InventoryItem} from "@/app/lib/actions/getItems";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {useQueryClient} from "@tanstack/react-query";

export default function CreateBorrowingDialog({
  user, item
                                              }: {
  user: UserInformation | null, item: InventoryItem | Inventory
}) {
  const [days, setDays] = useState(14);
  const [selectedUser, setSelectedUser] = useState(user);
  const query = useQueryClient();

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
      <UserSelectorFinder value={selectedUser} setValue={setSelectedUser} />
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
            if (selectedUser == null) return alert("Select a user");
            createBorrowing(selectedUser.id, item.id, days).then(r => {
              if (r.data) {
                query.invalidateQueries({ queryKey: ["reservations"] });
                query.invalidateQueries({ queryKey: ["borrowings"] });
                query.invalidateQueries({ queryKey: ["items"] });
                alert("Successfully created borrowing");
              }
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