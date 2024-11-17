import {Badge} from "@/components/ui/badge";
import {ExternalLink} from "lucide-react";
import Link from "next/link";
import {Book} from "@/app/lib/types/Book";

export default function BookBadgeInformation({ book, librarianLink }: { book: Book, librarianLink?: boolean }) {
  return <Link href={librarianLink ? `/librarian/book/${book.id}`: `/book/${book.id}`} target={"_blank"}>
    <Badge variant={"outline"}>{book.title} by {book.author} (Published by: {book.publisher}) (ID: {book.id}) <ExternalLink className={"w-4 ml-2"} /></Badge>
  </Link>;

}