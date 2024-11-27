import {getUserById} from "@/app/lib/actions/getUserById";
import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import ReservationsDataTable from "@/components/reservations/ReservationsDataTable";
import BorrowingsDataTable from "@/components/borrowings/BorrowingsDataTable";

export default async function Page({params}: {
  params: Promise<{ userId: number }>
}) {
  const {userId} = await params;
  const user = await getUserById(userId);

  if (!user) return <>Not found</>;

  return <>
    <Card>
      <CardHeader>
        <CardTitle>{user.firstName} {user.lastName} ({user.email})</CardTitle>
        <CardDescription>User Management</CardDescription>
      </CardHeader>
    </Card>

    <Card className={"mt-2"}>
      <CardHeader>
        <CardTitle className={"text-2xl"}>User Reservations</CardTitle>
      </CardHeader>
      <CardContent>
        <ReservationsDataTable userId={userId}/>
      </CardContent>
    </Card>

    <Card className={"mt-2"}>
      <CardHeader>
        <CardTitle className={"text-2xl"}>User Borrowings</CardTitle>
      </CardHeader>
      <CardContent>
        <BorrowingsDataTable userId={userId}/>
      </CardContent>
    </Card>
  </>
}