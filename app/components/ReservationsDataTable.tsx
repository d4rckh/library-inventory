"use client";

import {Reservation} from "@/app/lib/actions/getUserReservations";
import {useEffect, useState} from "react";
import {getReservations, ReservationFilters} from "@/app/lib/actions/getReservations";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {Table, TableBody, TableCell, TableHeader, TableRow} from "@/components/ui/table";
import ReservationTableRow from "@/app/components/ReservationTableRow";
import CreateBorrowingDialog from "@/app/components/CreateBorrowingDialog";

export default function ReservationsDataTable() {
  const [reservations, setReservations] = useState<Reservation[]>([]);

  const [reservationFilters, setReservationFilters] = useState<ReservationFilters>({

  });

  useEffect(() => {
    getReservations(reservationFilters).then((reservations) => {
      setReservations(reservations);
    });
  }, [reservationFilters]);

  return <>
    <Select value={ reservationFilters.isActive != undefined ? reservationFilters.isActive.toString() : "null" }
      onValueChange={(s) => setReservationFilters({
        ...reservationFilters,
        isActive: {
          "null": undefined,
          "false": false,
          "true": true
        }[s]
      })}
    >
      <b>Status</b>
      <SelectTrigger className="w-[180px]">
        <SelectValue placeholder="Status" />
      </SelectTrigger>
      <SelectContent>
        <SelectItem value="null">All</SelectItem>
        <SelectItem value="false">Picked-up/expired</SelectItem>
        <SelectItem value="true">Active</SelectItem>
      </SelectContent>
    </Select>
    <Table>
      <TableHeader>
        <TableRow>
          <TableCell>Reservation ID</TableCell>
          <TableCell>Book (Inventory ID)</TableCell>
          <TableCell>User</TableCell>
          <TableCell>Created at</TableCell>
          <TableCell>Pick-up by</TableCell>
          <TableCell>Status</TableCell>
          <TableCell>Actions</TableCell>
        </TableRow>
      </TableHeader>
      <TableBody>
          {
            reservations.map(reservation =>
              <ReservationTableRow reservation={reservation} userInfo={true} key={reservation.id}>
                <TableCell>
                  <CreateBorrowingDialog user={reservation.user} item={reservation.item} />
                </TableCell>
              </ReservationTableRow>
            )
          }
      </TableBody>
    </Table>
  </>
}