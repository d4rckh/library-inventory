"use client";

import {Dialog, DialogClose, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {useState} from "react";
import {createBorrowing} from "@/app/lib/actions/createBorrowing";
import UserSelectorFinder from "@/components/users/UserSelectorFinder";
import {InventoryItem} from "@/app/lib/actions/getItems";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {BookUp} from "lucide-react";

export default function CreateBorrowingDialog({
  user, item
                                              }: {
  user: UserInformation | null, item: InventoryItem | Inventory
}) {
  const [days, setDays] = useState(14);
  const [selectedUser, setSelectedUser] = useState(user);
  const query = useQueryClient();

  const { toast } = useToast();

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"icon"} variant={"secondary"}>
        <BookUp />
      </Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Create a new borrow</DialogTitle>
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
            if (selectedUser == null) return toast({
              title: "User not chosen",
            });
            createBorrowing(selectedUser.id, item.id, days).then(async r => {
              if (!r.error) {
                await query.invalidateQueries({ queryKey: ["reservations", "list"] });
                await query.invalidateQueries({ queryKey: ["borrowings", "list"] });
                await query.invalidateQueries({ queryKey: ["items", "list"] });
              }
              toast({
                title: r.error ? "Failed to register the borrowing" : "Successfully registered",
                description: r.error ? r.error.message : ""
              })
            });
          }}
        >
          Register
        </Button>
      </DialogClose>
    </DialogContent>
  </Dialog>;
}