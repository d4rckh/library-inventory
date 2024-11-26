"use server";

import ReserveItemButton from "@/components/reservations/ReserveItemButton";
import {TableCell, TableRow} from "@/components/ui/table";
import {Badge} from "@/components/ui/badge";
import {InventoryItem} from "@/app/lib/actions/getItems";

export default async function ItemTableRow({
                                             item,
                                           }: {
  item: InventoryItem,
}) {
  return <TableRow>
    <TableCell>{item.id}</TableCell>
    <TableCell>
      {item.reservation && <Badge variant={"outline"}>Reserved</Badge>}
      {item.borrowing && <Badge variant={"outline"}>Borrowed</Badge>}
      {!item.borrowing && !item.reservation && <Badge>Available</Badge>}
    </TableCell>
    <TableCell>{(!item.borrowing && !item.reservation) &&
        <ReserveItemButton itemId={item.id}/>}
      {(item.borrowing || item.reservation) &&
        <Badge variant={"outline"}>Item is unavailable</Badge>}
    </TableCell>
  </TableRow>
}