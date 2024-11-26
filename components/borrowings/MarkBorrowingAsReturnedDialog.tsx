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
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {BookCheck} from "lucide-react";

export default function MarkBorrowingAsReturnedDialog({
  borrowing
                                              }: {
  borrowing: Borrowing;
}) {
  const query = useQueryClient();
  const { toast } = useToast();

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"icon"} variant={"secondary"}>
        <BookCheck />
      </Button>
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
            markBorrowingAsReturned(borrowing.id).then(async r => {
              if (r.data) {
                await query.invalidateQueries({ queryKey: ["borrowings", "list"] })
                await query.invalidateQueries({ queryKey: ["items", "list"] });
                await query.invalidateQueries({ queryKey: ["reservations", "list"] });
              }

              toast({
                title: r.error ? "Error marking this borrowing as returned" : "Marked the borrowing as returned successfully",
                description: r.error ? r.error.message : ""
              })
            });
          }}
        >
          Confirm
        </Button>
      </DialogClose>
    </DialogContent>
  </Dialog>;
}