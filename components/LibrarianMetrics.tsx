import LibrarianMetric from "@/components/LibrarianMetric";
import {BookStats} from "@/app/lib/actions/getBookStats";
import { UserStats } from "@/app/lib/actions/getUserStats";
import {InventoryStats} from "@/app/lib/actions/getInventoryStats";
import {ReservationStats} from "@/app/lib/actions/getReservationStats";
import {BorrowingStats} from "@/app/lib/actions/getBorrowingStats";

export default function LibrarianMetrics({
  bookStats, inventoryStats, userStats, borrowStats, reservationStats
                                         }: {
  bookStats: BookStats, inventoryStats: InventoryStats, userStats: UserStats,
  borrowStats: BorrowingStats, reservationStats: ReservationStats,
}) {
  return <>
    <h2 className={"text-2xl mb-2"}>Stats</h2>
    <div className={"grid sm:grid-cols-6 grid-cols-1 gap-3 mb-2"}>
      <LibrarianMetric value={bookStats.uniqueTitles} title={"Unique titles"} />
      <LibrarianMetric value={inventoryStats.uniqueItems} title={"Items in inventory"} />
      <LibrarianMetric value={userStats.registeredUsers} title={"Registered users"} />
      <LibrarianMetric value={borrowStats.currentBorrows} title={"Borrowed books"} />
      <LibrarianMetric value={borrowStats.currentLateBorrows} title={"Late borrows"} />
      <LibrarianMetric value={reservationStats.currentReservations} title={"Active reservations"} />
    </div>
  </>
}