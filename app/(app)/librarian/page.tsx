import {getBorrowingStats} from "@/app/lib/actions/getBorrowingStats";
import {getReservationStats} from "@/app/lib/actions/getReservationStats";
import {getBookStats} from "@/app/lib/actions/getBookStats";
import {getInventoryStats} from "@/app/lib/actions/getInventoryStats";
import {getUserStats} from "@/app/lib/actions/getUserStats";
import LibrarianDashboard from "@/components/LibrarianDashboard";
import LibrarianMetrics from "@/components/LibrarianMetrics";

export default async function Page() {
  const borrowStats = await getBorrowingStats();
  const reservationStats = await getReservationStats();
  const bookStats = await getBookStats();
  const inventoryStats = await getInventoryStats();
  const userStats = await getUserStats();

  return <LibrarianDashboard
    metrics={<LibrarianMetrics bookStats={bookStats} inventoryStats={inventoryStats} userStats={userStats}
                               borrowStats={borrowStats} reservationStats={reservationStats}/>}/>;

}