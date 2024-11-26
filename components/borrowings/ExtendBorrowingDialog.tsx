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
import {Button} from "@/components/ui/button";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {markBorrowingAsReturned} from "@/app/lib/actions/markBorrowingAsReturned";
import {Badge} from "@/components/ui/badge";
import {Input} from "@/components/ui/input";
import {useState} from "react";
import {extendBorrowing} from "@/app/lib/actions/extendBorrowing";
import {useQueryClient} from "@tanstack/react-query";

export default function ExtendBorrowingDialog({
  borrowing
                                              }: {
  borrowing: Borrowing;
}) {
  const query = useQueryClient();

  const [days, setDays] = useState(3);

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"sm"} variant={"secondary"}>Extend</Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Extend this borrow</DialogTitle>
      </DialogHeader>
      <span>User: <UserBadgeInformation user={borrowing.user} /></span>
      <span>Book: <BookBadgeInformation book={borrowing.item.book} /></span>
      <span>Item ID: <Badge>{borrowing.itemId}</Badge></span>
      Extend by how many days?
      <Input type={"number"} value={days} onChange={(e) => setDays(parseInt(e.target.value))} />
      <DialogClose asChild>
        <Button
          onClick={() => {
            extendBorrowing(borrowing.id, days).then(r => {
              if (r.data) {
                query.invalidateQueries({
                  queryKey: ['borrowings']
                })
                alert("Successfully extended");
              }
              else if (r.error) alert(r.error.message);
            });
          }}
        >
          Confirm
        </Button>
      </DialogClose>
    </DialogContent>
  </Dialog>;
}