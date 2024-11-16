"use client";

import {TableCell, TableRow} from "@/components/ui/table";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Badge} from "@/components/ui/badge";
import DateDisplay from "@/app/components/DateDisplay";

export default function ReservationTableRow({
                                                    reservation
                                                  }: {
  reservation: Reservation
}) {
  const createdAt = new Date(reservation.createdAt);
  const expiresAt = new Date(reservation.expiresAt);
  const expired = !reservation.expiredAt && expiresAt < new Date();

  return <TableRow>
    <TableCell>{reservation.item.book.title} ({reservation.itemId})</TableCell>
    <TableCell>{reservation.userId}</TableCell>
    <TableCell><DateDisplay date={createdAt}/></TableCell>
    <TableCell><DateDisplay date={expiresAt}/></TableCell>
    <TableCell>{reservation.expiredAt ? <Badge>Yes</Badge> : (expired ? <Badge variant={"destructive"}>Expired</Badge> : <Badge variant={"outline"}>No</Badge>)}</TableCell>
  </TableRow>
}