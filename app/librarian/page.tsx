import {getBorrowingStats} from "@/app/lib/actions/getBorrowingStats";
import {getReservationStats} from "@/app/lib/actions/getReservationStats";
import {getBookStats} from "@/app/lib/actions/getBookStats";
import {getInventoryStats} from "@/app/lib/actions/getInventoryStats";
import LibrarianMetric from "@/app/components/LibrarianMetric";
import {getUserStats} from "@/app/lib/actions/getUserStats";

export default async function Page() {
  const borrowStats = await getBorrowingStats();
  const reservationStats = await getReservationStats();
  const bookStats = await getBookStats();
  const inventoryStats = await getInventoryStats();
  const userStats = await getUserStats();

  return <>
    <h2 className={"text-2xl mb-2"}>Stats</h2>
    <div className={"grid sm:grid-cols-3 grid-cols-1 gap-3 mb-2"}>
      <LibrarianMetric value={bookStats.uniqueTitles} title={"Unique titles"} />
      <LibrarianMetric value={inventoryStats.uniqueItems} title={"Items in inventory"} />
      <LibrarianMetric value={userStats.registeredUsers} title={"Registered users"} />
    </div>
    <div className={"grid sm:grid-cols-3 grid-cols-1 gap-3"}>
      <LibrarianMetric value={borrowStats.currentBorrows} title={"Borrowed books"} />
      <LibrarianMetric value={borrowStats.currentLateBorrows} title={"Late borrows"} />
      <LibrarianMetric value={reservationStats.currentReservations} title={"Active user"} />
    </div>
  </>
}