"use client";

import {useState} from "react";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {BorrowingFilters} from "@/app/lib/actions/getBorrowings";
import BorrowingTableRow from "@/components/borrowings/BorrowingTableRow";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import MarkBorrowingAsReturnedDialog from "@/components/borrowings/MarkBorrowingAsReturnedDialog";
import ExtendBorrowingDialog from "@/components/borrowings/ExtendBorrowingDialog";
import {useBooks, useBorrowings, useUsers} from "@/lib/queries/items";
import CellActions from "@/components/CellActions";
import {BookFilters} from "@/app/lib/actions/getBooks";
import {Input} from "@/components/ui/input";
import BookBadgeInformation from "@/components/books/BookBadgeInformation";
import DeleteBookDialog from "@/components/books/DeleteBookDialog";
import EditBookDialog from "@/components/books/EditBookDialog";
import {UserFilter} from "@/app/lib/actions/getUsers";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";

export default function UsersDataTable() {
  const [userFilters, setUserFilters] = useState<UserFilter>({
    emailSearch: ""
  });

  const {data, isSuccess, isPending } = useUsers(userFilters);

  return <>
    <b>Email:</b>
    <Input placeholder={"Search by email..."} value={userFilters.emailSearch} onChange={(e) => setUserFilters({
      ...userFilters,
      emailSearch: e.target.value
    })} />
    {
      isPending && "Loading..."
    }
    { isSuccess &&
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>User</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
            {
              data && data.map(user =>
                <TableRow key={user.id}>
                  <TableCell>{user.id}</TableCell>
                  <TableCell><UserBadgeInformation user={user} /></TableCell>
                </TableRow>
              )
            }
        </TableBody>
      </Table>
    }
  </>
}