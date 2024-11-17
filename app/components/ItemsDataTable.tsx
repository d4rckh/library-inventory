import ItemTableRow from "@/app/components/ItemTableRow";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {useEffect, useState} from "react";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {getItems} from "@/app/lib/actions/getItems";
import {Reservation} from "@/app/lib/actions/getUserReservations";
import {getItemBorrowingStats, ItemBorrowingStats} from "@/app/lib/actions/getItemBorrowingStats";
import {getItemValidReservation} from "@/app/lib/actions/getItemValidReservation";
import UserBadgeInformation from "@/app/components/UserBadgeInformation";
import {Badge} from "@/components/ui/badge";

export default function ItemsDataTable() {
  const [items, setItems] = useState<{ inv: Inventory, res: Reservation | null, stat: ItemBorrowingStats }[]>([]);

  useEffect(() => {
    getItems().then(async items => {

      setItems(await Promise.all(items.map(async item => {
        return {
          inv: item,
          res: await getItemValidReservation(item.id),
          stat: await getItemBorrowingStats(item.id)
        };
      })));
    })
  }, []);

  return <Table>
    <TableHeader>
      <TableRow>
        <TableHead>Item ID</TableHead>
        <TableHead>Reserved</TableHead>
        <TableHead>Borrowed</TableHead>
        <TableHead>Borrowed Times</TableHead>
        <TableHead>Reserve</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {items.map(item =>
        <TableRow key={item.inv.id}>
          <TableCell>
            {item.inv.id}
          </TableCell>
          <TableCell>
            {item.res && <UserBadgeInformation user={item.res.user} />}
            {!item.res && <Badge>No</Badge>}
          </TableCell>
          <TableCell>
            <Badge>{ item.stat.borrowed ? "Borrowed" : "No" }</Badge>
          </TableCell>
          <TableCell>
            <Badge>Borrowed { item.stat.times } times</Badge>
          </TableCell>
        </TableRow>
      )}
    </TableBody>
  </Table>
}