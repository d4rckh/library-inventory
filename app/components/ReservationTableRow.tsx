"use client";

import {TableCell, TableRow} from "@/components/ui/table";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Badge} from "@/components/ui/badge";
import DateDisplay from "@/app/components/DateDisplay";
import {ReactNode} from "react";

export default function ReservationTableRow({
                                                    reservation, userInfo, children
                                                  }: {
  reservation: Reservation, userInfo?: boolean, children?: ReactNode
}) {
  const createdAt = new Date(reservation.createdAt);
  const expiresAt = new Date(reservation.expiresAt);
  const expired = !reservation.expiredAt && expiresAt < new Date();

  return <TableRow>
    <TableCell className={"font-bold"}>{reservation.id}</TableCell>
    <TableCell>{reservation.item.book.title} ({reservation.itemId})</TableCell>
    { userInfo && <TableCell><Badge variant={"outline"}>{reservation.user.email} (ID: {reservation.userId})</Badge></TableCell> }
    <TableCell><DateDisplay date={createdAt}/></TableCell>
    <TableCell><DateDisplay date={expiresAt}/></TableCell>
    <TableCell>{reservation.expiredAt ? <Badge>Picked-up</Badge> : (expired ? <Badge variant={"destructive"}>Expired</Badge> : <Badge variant={"outline"}>Active</Badge>)}</TableCell>
    {children}
  </TableRow>
}