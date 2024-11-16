import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import ItemTableRow from "@/app/components/ItemTableRow";
import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";

export default function ItemsTable({items, showBorrowedTimes}: {
  items: Inventory[];
  showBorrowedTimes?: boolean
}) {
  // return <div className={"grid grid-cols-3 mt-3 gap-3"}>
  //     {
  //         items.map(item =>
  //             <ItemTableRow.tsx item={item} key={item.id} />
  //         )
  //     }
  // </div>
  return <Table>
    <TableHeader>
      <TableRow>
        <TableHead>Item ID</TableHead>
        <TableHead>Reserved</TableHead>
        <TableHead>Borrowed</TableHead>
        {showBorrowedTimes && <TableHead>Borrowed Times</TableHead>}
        <TableHead>Reserve</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {items.map(item =>
        <ItemTableRow item={item} key={item.id} showBorrowedTimes={showBorrowedTimes}/>
      )}
    </TableBody>
  </Table>
}