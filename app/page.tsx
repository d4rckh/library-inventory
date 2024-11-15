import {getAllBooks} from "@/app/lib/actions/getBooks";
import SignInForm from "@/app/components/SignInForm";
import {getUser} from "@/app/lib/actions/getUser";
import CreateBookForm from "@/app/components/CreateBookForm";
import BookGrid from "@/app/components/BookGrid";

export default async function Home() {
  const books = await getAllBooks();
  const user = await getUser();

  return (
    <>
      {!user && <SignInForm />}
      {user && "Logged in as " + user.email}
      {user && <CreateBookForm />}

      <BookGrid books={books} />
    </>
  );
}
