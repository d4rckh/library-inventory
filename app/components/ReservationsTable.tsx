import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import ReservationTableRow from "@/app/components/ReservationTableRow";

export default function ReservationsTable({reservations}: {
  reservations: Reservation[];
}) {
  return <Table className={"mt-2"}>
    <TableHeader>
      <TableRow>
        <TableHead>Book Title (Inventory ID)</TableHead>
        <TableHead>User ID</TableHead>
        <TableHead>Reserved at</TableHead>
        <TableHead>Pick-up by</TableHead>
        <TableHead>Picked-up?</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {reservations.map(reservation =>
        <ReservationTableRow reservation={reservation} key={reservation.id}/>
      )}
    </TableBody>
  </Table>
}