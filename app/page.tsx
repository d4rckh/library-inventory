import {getAllBooks} from "@/app/lib/actions/getBooks";
import SignInForm from "@/app/components/SignInForm";
import {getLoggedInUser} from "@/app/lib/actions/getLoggedInUser";
import CreateBookForm from "@/app/components/CreateBookForm";
import BooksGrid from "@/app/components/BooksGrid";

export default async function Home() {
  const books = await getAllBooks();
  const user = await getLoggedInUser();

  return (
    <>
      {!user && <SignInForm />}
      {user && "Logged in as " + user.email}
      {user && <CreateBookForm />}

      <BooksGrid books={books} />
    </>
  );
}
