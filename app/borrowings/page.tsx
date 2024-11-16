import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import {redirect} from "next/navigation";
import {getUserBorrowings} from "@/app/lib/actions/getUserBorrowings";
import BorrowingsTable from "@/app/components/BorrowingsTable";
import {getUserReservations} from "@/app/lib/actions/getUserReservations";
import ReservationsTable from "@/app/components/ReservationsTable";

export default async function Page() {
  const user = await getLoggedInUser();

  if (!user) redirect("/");

  const borrowings = await getUserBorrowings(user.id);
  const reservations = await getUserReservations(user.id);

  return <>
    <h2 className={"text-2xl mt-5"}>My Borrowings</h2>
    <BorrowingsTable borrowings={borrowings}/>
    <h2 className={"text-2xl mt-5"}>My Reservations</h2>
    <ReservationsTable reservations={reservations}/>
  </>
}