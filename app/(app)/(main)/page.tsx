import BooksGridAndSearch from "@/components/books/BooksGridAndSearch";

export default async function Home() {
  return (
    <>
      <h1 className={"text-5xl mb-2"}>Books</h1>
      <BooksGridAndSearch/>
    </>
  );
}
