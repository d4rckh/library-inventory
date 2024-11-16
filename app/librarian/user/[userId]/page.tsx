import {getUserById} from "@/app/lib/actions/getUserById";
import {Card, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";

export default async function Page({params}: {
  params: Promise<{ userId: number }>
}) {
  const { userId } = await params;
  const user = await getUserById(userId);

  if (!user) return <>Not found</>;

  return <>
    <Card>
      <CardHeader>
        <CardTitle>{user.firstName} {user.lastName} ({user.email})</CardTitle>
        <CardDescription>User Management</CardDescription>
      </CardHeader>
    </Card>
  </>
}