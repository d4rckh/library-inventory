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
import UserBadgeInformation from "@/components/UserBadgeInformation";
import BookBadgeInformation from "@/components/BookBadgeInformation";
import {markBorrowingAsReturned} from "@/app/lib/actions/markBorrowingAsReturned";
import {Badge} from "@/components/ui/badge";

export default function MarkBorrowingAsReturnedDialog({
  borrowing, refreshData
                                              }: {
  borrowing: Borrowing; refreshData: () => void;
}) {

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"sm"} variant={"secondary"}>Mark as returned</Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Mark as returned</DialogTitle>
        <DialogDescription>
          Are you sure you want to mark this borrowing as returned?
        </DialogDescription>
      </DialogHeader>
      <span>User: <UserBadgeInformation user={borrowing.user} /></span>
      <span>Book: <BookBadgeInformation book={borrowing.item.book} /></span>
      <span>Item ID: <Badge>{borrowing.itemId}</Badge></span>
      <DialogClose asChild>
        <Button
          onClick={() => {
            markBorrowingAsReturned(borrowing.id).then(r => {
              if (r.data) {
                refreshData();
                alert("Successfully marked as returned");
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