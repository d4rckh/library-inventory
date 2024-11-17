import {TableCell, TableRow} from "@/components/ui/table";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import DateDisplay from "@/app/components/DateDisplay";
import {Badge} from "@/components/ui/badge";
import DurationDisplay from "@/app/components/DurationDisplay";
import UserBadgeInformation from "@/app/components/UserBadgeInformation";

export default function BorrowingTableRow({
                                                  borrowing, userInfo
                                                }: {
  borrowing: Borrowing, userInfo?: boolean
}) {

  return <TableRow>
    <TableCell>{borrowing.item.book.title} ({borrowing.itemId})</TableCell>
    { userInfo && <TableCell><UserBadgeInformation user={borrowing.user} /></TableCell> }
    <TableCell><DateDisplay date={new Date(borrowing.borrowDate)} /></TableCell>
    <TableCell><DateDisplay date={new Date(borrowing.returnDate)} /></TableCell>
    <TableCell>{borrowing.returnedDate ? "-" : <DurationDisplay targetDate={new Date(borrowing.returnDate)} />}</TableCell>
    <TableCell>{borrowing.returnedDate ? <Badge variant={"outline"}>Yes</Badge> : <Badge>No</Badge>}</TableCell>
  </TableRow>
}