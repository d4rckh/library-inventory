"use client";

import {useState} from "react";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {BorrowingFilters} from "@/app/lib/actions/getBorrowings";
import BorrowingTableRow from "@/components/borrowings/BorrowingTableRow";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import MarkBorrowingAsReturnedDialog from "@/components/borrowings/MarkBorrowingAsReturnedDialog";
import ExtendBorrowingDialog from "@/components/borrowings/ExtendBorrowingDialog";
import {useBooks, useBorrowings} from "@/lib/queries/items";
import CellActions from "@/components/CellActions";
import {BookFilters} from "@/app/lib/actions/getBooks";
import {Input} from "@/components/ui/input";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import DeleteBookDialog from "@/components/books/DeleteBookDialog";
import EditBookDialog from "@/components/books/EditBookDialog";

export default function BooksDataTable() {
  const [bookFilters, setBookFilters] = useState<BookFilters>({
    titleSearch: ""
  });

  const {data, isSuccess, isPending } = useBooks(bookFilters);

  return <>
    <b>Title:</b>
    <Input placeholder={"Search by title..."} value={bookFilters.titleSearch} onChange={(e) => setBookFilters({
      ...bookFilters,
      titleSearch: e.target.value
    })} />
    {
      isPending && "Loading..."
    }
    { isSuccess &&
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Book</TableHead>
            <TableHead>Total Items</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
            {
              data && data.map(book =>
                <TableRow key={book.id}>
                  <TableCell>{book.id}</TableCell>
                  <TableCell><BookBadgeInformation librarianLink book={book} /></TableCell>
                  <TableCell>{book.totalItems}</TableCell>
                  <TableCell>
                    <CellActions>
                      <DeleteBookDialog book={book} />
                      <EditBookDialog book={book} />
                    </CellActions>
                  </TableCell>
                </TableRow>
              )
            }
        </TableBody>
      </Table>
    }
  </>
}