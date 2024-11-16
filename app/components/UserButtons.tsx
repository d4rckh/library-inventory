import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import {Button} from "@/components/ui/button";
import Link from "next/link";

export default async function UserButtons() {
  const user = await getLoggedInUser();

  return <>
    {user && <Link href={"/borrowings"}>
        <Button variant={"outline"}>
            My borrowings & reservations
        </Button>
    </Link>}
    <Link href={"/account"}>
      <Button variant="outline">
        {
          user ? "Logged in as " + user.email : "Log in"
        }
      </Button>
    </Link></>;
}