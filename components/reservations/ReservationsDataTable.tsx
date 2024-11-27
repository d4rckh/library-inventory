"use client";

import {Reservation} from "@/app/lib/actions/getUserReservations";
import {useEffect, useState} from "react";
import {getReservations, ReservationFilters} from "@/app/lib/actions/getReservations";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import ReservationTableRow from "@/components/reservations/ReservationTableRow";
import CreateBorrowingDialog from "@/components/borrowings/CreateBorrowingDialog";
import CancelReservationDialog from "@/components/reservations/CancelReservationDialog";
import {useReservations} from "@/lib/queries/items";
import CellActions from "@/components/CellActions";

export default function ReservationsDataTable({
  userId
                                              }: {
  userId?: number
}) {
  const [reservationFilters, setReservationFilters] = useState<ReservationFilters>({
    userId
  });

  const { data, isPending, isSuccess } = useReservations(reservationFilters);

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
    {
      isPending && "Loading..."
    }
    { isSuccess &&
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Book</TableHead>
            <TableHead>Item ID</TableHead>
            <TableHead>User</TableHead>
            <TableHead>Created at</TableHead>
            <TableHead>Pick-up by</TableHead>
            <TableHead>Status</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
            {
              data.map(reservation =>
                <ReservationTableRow reservation={reservation} userInfo={true} key={reservation.id}>
                  <TableCell>
                    <CellActions>
                      <CreateBorrowingDialog user={reservation.user} item={reservation.item} />
                      {!reservation.expiredAt && !reservation.cancelled && <CancelReservationDialog reservation={reservation} />}
                    </CellActions>
                  </TableCell>
                </ReservationTableRow>
              )
            }
        </TableBody>
      </Table>
    }
  </>
}