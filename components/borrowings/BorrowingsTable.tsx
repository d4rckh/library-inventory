import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import BorrowingTableRow from "@/components/borrowings/BorrowingTableRow";

export default function BorrowingsTable({borrowings}: {
  borrowings: Borrowing[];
}) {
  return <Table className={"mt-2"}>
    <TableHeader>
      <TableRow>
        <TableHead>ID</TableHead>
        <TableHead>Book</TableHead>
        <TableHead>Item ID</TableHead>
        <TableHead>Borrowed at</TableHead>
        <TableHead>Should return at</TableHead>
        <TableHead>Time left</TableHead>
        <TableHead>Returned?</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {borrowings.map(borrowing =>
        <BorrowingTableRow borrowing={borrowing} key={borrowing.id}/>
      )}
    </TableBody>
  </Table>
}