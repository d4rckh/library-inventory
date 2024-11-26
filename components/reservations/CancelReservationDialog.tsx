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
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {Badge} from "@/components/ui/badge";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {cancelReservation} from "@/app/lib/actions/cancelReservation";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {CircleX} from "lucide-react";

export default function CancelReservationDialog({
  reservation
                                              }: {
  reservation: Reservation;
}) {
  const query = useQueryClient();
  const { toast } = useToast();

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"icon"} variant={"destructive"}>
        <CircleX />
      </Button>
    </DialogTrigger>
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Cancel Reservation</DialogTitle>
        <DialogDescription>
          Are you sure you want to cancel this reservation?
        </DialogDescription>
      </DialogHeader>
      <span>User: <UserBadgeInformation user={reservation.user} /></span>
      <span>Book: <BookBadgeInformation book={reservation.item.book} /></span>
      <span>Item ID: <Badge>{reservation.itemId}</Badge></span>
      <DialogClose asChild>
        <Button
          variant={"destructive"}
          onClick={() => {
            cancelReservation(reservation.id).then(async r => {
              if (!r.error) {
                await query.invalidateQueries({ queryKey: ["reservations", "list"] });
                await query.invalidateQueries({ queryKey: ["borrowings", "list"] });
                await query.invalidateQueries({ queryKey: ["items", "list"] });
              }

              toast({
                title: r.error ? "Failed to cancel reservation" : "Successfully cancelled reservation",
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