"use server";

import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {getItemBorrowingStats} from "@/app/lib/actions/getItemBorrowingStats";
import ReserveItemButton from "@/app/components/ReserveItemButton";
import {getItemValidReservation} from "@/app/lib/actions/getItemValidReservation";
import {TableCell, TableRow} from "@/components/ui/table";
import {Badge} from "@/components/ui/badge";

export default async function ItemTableRow({
                                             item,
                                             showBorrowedTimes,
                                           }: {
  item: Inventory,
  showBorrowedTimes?: boolean,
}) {
  const borrowingStats = await getItemBorrowingStats(item.id);
  const validReservation = await getItemValidReservation(item.id);

  return <TableRow>
    <TableCell>{item.id}</TableCell>
    <TableCell>{validReservation ? <Badge variant={"outline"}>Yes</Badge> : <Badge>No</Badge>}</TableCell>
    <TableCell>{borrowingStats.borrowed ? <Badge variant={"outline"}>Yes</Badge> : <Badge>No</Badge>}</TableCell>
    {showBorrowedTimes && <TableCell><Badge>{borrowingStats.borrowed} times</Badge></TableCell>}
    <TableCell>{(!validReservation && !borrowingStats.borrowed) &&
        <ReserveItemButton itemId={item.id}/>}
      {!(!validReservation && !borrowingStats.borrowed) &&
        <Badge variant={"outline"}>Item is unavailable</Badge>}
    </TableCell>
  </TableRow>
}