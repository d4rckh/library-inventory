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

export default function CancelReservationDialog({
  reservation
                                              }: {
  reservation: Reservation;
}) {
  const query = useQueryClient();

  return <Dialog>
    <DialogTrigger asChild>
      <Button size={"sm"} variant={"destructive"}>Cancel Reservation</Button>
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
            cancelReservation(reservation.id).then(r => {
              if (r.data) {
                query.invalidateQueries({ queryKey: ["reservations"] });
                query.invalidateQueries({ queryKey: ["borrowings"] });
                alert("Successfully cancelled reservation");
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