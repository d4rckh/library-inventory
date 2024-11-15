import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {getItemBorrowingStats} from "@/app/lib/actions/getItemBorrowingStats";
import ReserveItemButton from "@/app/components/ReserveItemButton";
import {getItemValidReservation} from "@/app/lib/actions/getItemValidReservation";

export default async function ItemCard({
  item
                                       }: {
  item: Inventory
}) {
  const borrowingStats = await getItemBorrowingStats(item.id);
  const validReservation = await getItemValidReservation(item.id);

  return <Card>
    <CardHeader>
      <CardTitle>ID: {item.id}</CardTitle>
    </CardHeader>
    <CardContent>
      {borrowingStats.data && <>
          <p>Has been borrowed {borrowingStats.data.times} times</p>
      </>}
      { validReservation && <p>Item is currently reserved</p> }
      { borrowingStats.data?.borrowed && <p>Item is currently borrowed</p> }
      { !validReservation && !borrowingStats.data?.borrowed && <ReserveItemButton itemId={item.id} /> }
    </CardContent>
  </Card>
}