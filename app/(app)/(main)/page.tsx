import {getBooks} from "@/app/lib/actions/getBooks";
import BooksGridAndSearch from "@/components/books/BooksGridAndSearch";

export default async function Home() {
  const books = await getBooks();
  return (
    <>
      <BooksGridAndSearch initialBooks={books}/>
    </>
  );
}
