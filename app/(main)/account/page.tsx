import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import SignInForm from "@/components/SignInForm";

export default async function Page() {
  const user = await getLoggedInUser();

  if (!user) {
    return <SignInForm/>
  };

  return <>
    <h2 className={"text-2xl"}>Hello {user.email}</h2>
  </>
}