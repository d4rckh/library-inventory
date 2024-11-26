import {UserInformation} from "@/app/lib/actions/getLoggedInUser";
import UserBadgeInformation from "@/components/users/UserBadgeInformation";
import {EditIcon} from "lucide-react";
import {Button} from "@/components/ui/button";
import {useEffect, useState} from "react";
import {Input} from "@/components/ui/input";
import {getUsers} from "@/app/lib/actions/getUsers";

export default function UserSelectorFinder({ value, setValue }: {
  value: UserInformation | null,
  setValue: (value: UserInformation) => void
}) {
  const [editing, setEditing] = useState(value == null);
  const [searchedResults, setSearchedResults] = useState<UserInformation[]>([]);
  const [email, setEmail] = useState("");

  useEffect(() => {
    if (editing && email.length > 3) {
      getUsers({ emailSearch: email }).then(r => {
        setSearchedResults(r);
      })
    }
  }, [editing, email]);

  return <>
    {value && !editing &&
        <span className={"flex flex-row items-center gap-1"}>
            <span>Selected user:</span>
            <UserBadgeInformation user={value} />
            <Button className={"w-7 h-7"} onClick={() => setEditing(true)}><EditIcon className={"w-5"} /></Button>
        </span>}
    { editing && <>
        Find user by email: <Input onChange={(e) => setEmail(e.target.value)} value={email} type="email" placeholder={"Email"} />
      <div className={"flex flex-col gap-[1px]"}>
        { searchedResults.map(user =>
          <Button key={user.id} onClick={() => {
            setEditing(false);
            setValue(user);
          }}>Select {user.email}</Button>
        ) }
      </div>
    </> }
  </>
}