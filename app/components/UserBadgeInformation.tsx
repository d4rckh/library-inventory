import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import {Badge} from "@/components/ui/badge";
import {ExternalLink} from "lucide-react";
import Link from "next/link";

export default function UserBadgeInformation({ user }: { user: UserInformation }) {

  return <Link href={`/librarian/user/${user.id}`} target={"_blank"}>
    <Badge variant={"outline"}>{user.email} (ID: {user.id}) <ExternalLink className={"w-4 ml-2"} /></Badge>
  </Link>;

}