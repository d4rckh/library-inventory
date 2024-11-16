import {getAllBooks} from "@/app/lib/actions/getBooks";
import BooksGrid from "@/app/components/BooksGrid";

export default async function Home() {
  const books = await getAllBooks();
  return (
    <>
      <BooksGrid books={books}/>
    </>
  );
}
