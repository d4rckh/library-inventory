"use client";

import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import ReservationTableRow from "@/components/reservations/ReservationTableRow";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {useReservations} from "@/lib/queries/items";

export default function ReservationsTable({user}: {
  user: UserInformation;
}) {
  const {data: reservations, isSuccess, isPending} = useReservations({ userId: user.id });

  if (isPending) return <>Fetching...</>;
  if (!isSuccess && !isPending) return <>Failed to fetch reservations</>;

  return <Table className={"mt-2"}>
    <TableHeader>
      <TableRow>
        <TableHead>Reservation ID</TableHead>
        <TableHead>Book Title (Inventory ID)</TableHead>
        <TableHead>Reserved at</TableHead>
        <TableHead>Pick-up by</TableHead>
        <TableHead>Status</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {reservations.map(reservation =>
        <ReservationTableRow reservation={reservation} key={reservation.id}/>
      )}
    </TableBody>
  </Table>
}