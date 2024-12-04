import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import {Button} from "@/components/ui/button";
import Link from "next/link";

export default async function UserButtons() {
  const user = await getLoggedInUser();

  return <>
    {user && <Link href={"/borrowings"}>
        <Button variant={"link"}>
            My borrowings & reservations
        </Button>
    </Link>}
    {
      user?.isLibrarian &&
        <Link href={"/librarian"}>
          <Button variant={"link"}>
                Librarian Management
            </Button>
        </Link>
    }
    <Link href={"/account"}>
      <Button variant={"link"}>
        {
          user ? "Logged in as " + user.email : "Log in"
        }
      </Button>
    </Link></>;
}