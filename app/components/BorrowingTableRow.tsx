import {TableCell, TableRow} from "@/components/ui/table";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import DateDisplay from "@/app/components/DateDisplay";
import {Badge} from "@/components/ui/badge";

export default async function BorrowingTableRow({
                                                  borrowing
                                                }: {
  borrowing: Borrowing
}) {

  return <TableRow>
    <TableCell>{borrowing.item.book.title} ({borrowing.itemId})</TableCell>
    <TableCell>{borrowing.userId}</TableCell>
    <TableCell><DateDisplay date={new Date(borrowing.borrowDate)} /></TableCell>
    <TableCell><DateDisplay date={new Date(borrowing.returnDate)} /></TableCell>
    <TableCell>{borrowing.returnedDate ? <Badge variant={"outline"}>Yes</Badge> : <Badge>No</Badge>}</TableCell>
  </TableRow>
}