import "../globals.css";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import UserButtons from "@/components/users/UserButtons";
import QueryProvider from "@/lib/queryProvider";

export default function RootLayout({
                                     children,
                                   }: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">

    <body>

    <div className={"flex flex-row gap-1 p-1"}>
      <Link href={"/"}>
        <Button variant={"outline"}>
          Home
        </Button>
      </Link>
      <UserButtons/>
    </div>

    <div className={"px-3"}>
      <QueryProvider>
        {children}
      </QueryProvider>
    </div>

    </body>
    </html>
  );
}
