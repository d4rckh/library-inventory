import ItemTableRow from "@/components/inventory/ItemTableRow";
import {Table, TableBody, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {InventoryItem} from "@/app/lib/actions/getItems";

export default function ItemsTable({items}: {
  items: InventoryItem[];
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