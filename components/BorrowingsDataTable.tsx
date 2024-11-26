"use client";

import {useState} from "react";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {BorrowingFilters} from "@/app/lib/actions/getBorrowings";
import BorrowingTableRow from "@/components/BorrowingTableRow";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import MarkBorrowingAsReturnedDialog from "@/components/MarkBorrowingAsReturnedDialog";
import ExtendBorrowingDialog from "@/components/ExtendBorrowingDialog";
import {useBorrowings} from "@/lib/queries/items";

export default function BorrowingsDataTable({ userId }: { userId?: number }) {
  const [borrowingFilters, setBorrowingFilters] = useState<BorrowingFilters>({
    userId
  });

  const {data} = useBorrowings(borrowingFilters);

  return <>
    <Select value={ borrowingFilters.isReturned != undefined ? borrowingFilters.isReturned.toString() : "null" }
            onValueChange={(s) => setBorrowingFilters({
              ...borrowingFilters,
              isReturned: {
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
        <SelectItem value="false">Not returned</SelectItem>
        <SelectItem value="true">Returned</SelectItem>
      </SelectContent>
    </Select>
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>ID</TableHead>
          <TableHead>Book</TableHead>
          <TableHead>Item ID</TableHead>
          <TableHead>User</TableHead>
          <TableHead>Borrowed at</TableHead>
          <TableHead>Should return at</TableHead>
          <TableHead>Time left</TableHead>
          <TableHead>Returned?</TableHead>
          <TableHead>Actions</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
          {
            data && data.map(borrowing =>
              <BorrowingTableRow borrowing={borrowing} userInfo librarianLink key={borrowing.id}>
                <TableCell>
                  {!borrowing.returnedDate && <MarkBorrowingAsReturnedDialog borrowing={borrowing} />}
                  {!borrowing.returnedDate && <ExtendBorrowingDialog borrowing={borrowing} />}
                </TableCell>
              </BorrowingTableRow>
            )
          }
      </TableBody>
    </Table>
  </>
}