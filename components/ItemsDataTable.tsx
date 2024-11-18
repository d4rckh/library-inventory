"use client";

import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {useEffect, useState} from "react";
import {getItems, InventoryDto} from "@/app/lib/actions/getItems";
import UserBadgeInformation from "@/components/UserBadgeInformation";
import {Badge} from "@/components/ui/badge";
import CreateBorrowingDialog from "@/components/CreateBorrowingDialog";
import BookBadgeInformation from "@/components/BookBadgeInformation";

export default function ItemsDataTable({ bookId }: { bookId?: number }) {
  const [items, setItems] = useState<InventoryDto[]>([]);

  const refreshData = () => {
    getItems({ bookId }).then(items => {
      setItems(items);
    });
  }

  useEffect(() => {
    refreshData();
  }, [bookId]);

  return <Table>
    <TableHeader>
      <TableRow>
        <TableHead>ID</TableHead>
        <TableHead>Book</TableHead>
        <TableHead>Status</TableHead>
        <TableHead>Borrowed Times</TableHead>
        <TableHead>Actions</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {items.map(item =>
        <TableRow key={item.id}>
          <TableCell>
            {item.id}
          </TableCell>
          <TableCell>
            <BookBadgeInformation book={item.book} librarianLink />
          </TableCell>
          <TableCell>
            { item.reservation && <>Reserved by <UserBadgeInformation user={item.reservation.user}/></> }
            { item.borrowing && <>Borrowed by <UserBadgeInformation user={item.borrowing.user}/></> }
            { !item.borrowing && !item.reservation && <Badge>Available</Badge>}
          </TableCell>
          <TableCell>
            <Badge variant={"secondary"}>Borrowed { item.stats.times } times</Badge>
          </TableCell>
          <TableCell>
            { !item.borrowing && !item.reservation && <CreateBorrowingDialog user={null} item={item} refreshData={refreshData} />}
          </TableCell>
        </TableRow>
      )}
    </TableBody>
  </Table>
}