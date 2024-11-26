"use client";

import {TableCell, TableRow} from "@/components/ui/table";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {Badge} from "@/components/ui/badge";
import DateDisplay from "@/components/DateDisplay";
import {ReactNode} from "react";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";

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
    <TableCell><BookBadgeInformation book={reservation.item.book} librarianLink /></TableCell>
    <TableCell>{reservation.itemId}</TableCell>
    { userInfo && <TableCell><UserBadgeInformation user={reservation.user} /></TableCell> }
    <TableCell><DateDisplay date={createdAt}/></TableCell>
    <TableCell><DateDisplay date={expiresAt}/></TableCell>
    <TableCell>
      {reservation.expiredAt ? <Badge variant={"outline"}>Picked-up</Badge> : (expired ? <Badge variant={"destructive"}>Expired</Badge> : (reservation.cancelled ? <Badge variant={"destructive"}>Cancelled</Badge> : <Badge>Active</Badge>))}</TableCell>
    {children}
  </TableRow>
}