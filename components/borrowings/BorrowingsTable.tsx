"use client";

import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import BorrowingTableRow from "@/components/borrowings/BorrowingTableRow";
import {useBorrowings} from "@/lib/queries/items";
import {UserInformation} from "@/app/lib/actions/getLoggedInUser";

export default function BorrowingsTable({user}: {
  user: UserInformation
}) {
  const {data, isSuccess} = useBorrowings({ userId: user.id });

  if (!isSuccess)
    return <>Failed to fetch borrowings</>;

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
      {data.map(borrowing =>
        <BorrowingTableRow borrowing={borrowing} key={borrowing.id}/>
      )}
    </TableBody>
  </Table>
}