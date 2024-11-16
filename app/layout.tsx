import "./globals.css";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import UserButtons from "@/app/components/UserButtons";

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

    <div className={`max-w-5xl mx-auto pt-5`}>
      <h1 className={"text-4xl mb-5 text-center"}>Library</h1>
      {children}
    </div>

    </body>
    </html>
  );
}
