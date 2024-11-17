import ItemTableRow from "@/components/ItemTableRow";
import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {InventoryDto} from "@/app/lib/actions/getItems";

export default function ItemsTable({items}: {
  items: InventoryDto[];
}) {
  return <Table>
    <TableHeader>
      <TableRow>
        <TableHead>Item ID</TableHead>
        <TableHead>Status</TableHead>
        <TableHead>Reserve</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {items.map(item =>
        <ItemTableRow item={item} key={item.id} />
      )}
    </TableBody>
  </Table>
}