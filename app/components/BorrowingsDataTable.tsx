"use client";

import {Suspense, useEffect, useState} from "react";
import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Borrowing} from "@/app/lib/actions/getUserBorrowings";
import {BorrowingFilters, getBorrowings} from "@/app/lib/actions/getBorrowings";
import BorrowingTableRow from "@/app/components/BorrowingTableRow";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

export default function BorrowingsDataTable() {
  const [borrowings, setBorrowings] = useState<Borrowing[]>([]);

  const [borrowingFilters, setBorrowingFilters] = useState<BorrowingFilters>({

  });

  useEffect(() => {
    getBorrowings(borrowingFilters).then((borrowings) => {
      setBorrowings(borrowings);
    });
  }, [borrowingFilters]);

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
          <TableHead>Book Title (Inventory ID)</TableHead>
          <TableHead>User</TableHead>
          <TableHead>Borrowed at</TableHead>
          <TableHead>Should return at</TableHead>
          <TableHead>Time left</TableHead>
          <TableHead>Returned?</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
          {
            borrowings.map(borrowing =>
              <Suspense  key={borrowing.id} fallback={"loading"}>
                <BorrowingTableRow borrowing={borrowing} userInfo />
              </Suspense>
            )
          }
      </TableBody>
    </Table>
  </>
}