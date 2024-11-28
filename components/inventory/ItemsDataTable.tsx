"use client";

import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import {Badge} from "@/components/ui/badge";
import CreateBorrowingDialog from "@/components/borrowings/CreateBorrowingDialog";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import {useItems} from "@/lib/queries/items";
import CellActions from "@/components/CellActions";
import DeleteItemDialog from "@/components/inventory/DeleteItemDialog";

export default function ItemsDataTable({ bookId }: { bookId?: number }) {

  const { data, isSuccess, isPending, isError } = useItems({ bookId });

  return <>
    {isError && "Error loading items"}
    {isPending && "Loading..."}
    { isSuccess && <Table>
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
        {data.map(item =>
          <TableRow key={item.id}>
            <TableCell>
              {item.id}
            </TableCell>
            <TableCell>
              <BookBadgeInformation book={item.book} librarianLink />
            </TableCell>
            <TableCell>
              { item.reservation && <span className={"flex flex-row gap-1"}>Reserved by <UserBadgeInformation user={item.reservation.user}/></span> }
              { item.borrowing && <span className={"flex flex-row gap-1"}>Borrowed by <UserBadgeInformation user={item.borrowing.user}/></span> }
              { !item.borrowing && !item.reservation && <Badge>Available</Badge>}
            </TableCell>
            <TableCell>
              <Badge variant={"secondary"}>Borrowed { item.stats.times } times</Badge>
            </TableCell>
            <TableCell>
              <CellActions>
                { !item.borrowing && !item.reservation && <CreateBorrowingDialog user={null} item={item} />}
                <DeleteItemDialog item={item} />
              </CellActions>
            </TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
    }
  </>;
}