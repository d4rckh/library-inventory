import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import {redirect} from "next/navigation";
import BorrowingsTable from "@/components/borrowings/BorrowingsTable";
import {getUserReservations} from "@/app/lib/actions/getUserReservations";
import ReservationsTable from "@/components/reservations/ReservationsTable";

export default async function Page() {
  const user = await getLoggedInUser();

  if (!user) redirect("/");

  const reservations = await getUserReservations(user.id);

  return <>
    <h2 className={"text-2xl mt-5"}>My Borrowings</h2>
    <BorrowingsTable user={user}/>
    <h2 className={"text-2xl mt-5"}>My Reservations</h2>
    <ReservationsTable reservations={reservations}/>
  </>
}