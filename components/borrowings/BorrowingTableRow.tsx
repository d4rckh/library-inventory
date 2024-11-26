import {TableCell, TableRow} from "@/components/ui/table";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import DateDisplay from "@/components/DateDisplay";
import {Badge} from "@/components/ui/badge";
import DurationDisplay from "@/components/DurationDisplay";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {ReactNode} from "react";

export default function BorrowingTableRow({
                                                  borrowing, userInfo, librarianLink, children
                                                }: {
  borrowing: Borrowing, userInfo?: boolean, librarianLink?: boolean, children?: ReactNode
}) {

  return <TableRow>
    <TableCell>{borrowing.id}</TableCell>
    <TableCell><BookBadgeInformation book={borrowing.item.book} librarianLink={librarianLink} /></TableCell>
    <TableCell>{borrowing.itemId}</TableCell>
    { userInfo && <TableCell><UserBadgeInformation user={borrowing.user} /></TableCell> }
    <TableCell><DateDisplay date={new Date(borrowing.borrowDate)} /></TableCell>
    <TableCell><DateDisplay date={new Date(borrowing.returnDate)} /></TableCell>
    <TableCell>{borrowing.returnedDate ? "-" : <DurationDisplay targetDate={new Date(borrowing.returnDate)} />}</TableCell>
    <TableCell>{borrowing.returnedDate ? <Badge variant={"outline"}>Yes</Badge> : <Badge>No</Badge>}</TableCell>
    {children}
  </TableRow>
}