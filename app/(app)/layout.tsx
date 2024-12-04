import "../globals.css";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import UserButtons from "@/components/users/UserButtons";
import QueryProviderAndToaster from "@/lib/queryProviderAndToaster";
import React, {ReactNode} from "react";

export default function RootLayout({
                                     children,
                                   }: Readonly<{
  children: ReactNode;
}>) {
  return (
    <html lang="en">

    <body>

    <div className={"flex flex-row gap-1 p-1 border-gray-200 border-b-2 bg-gray-100 mb-5"}>
      <Link href={"/"}>
        <Button variant={"link"}>
          Home
        </Button>
      </Link>
      <UserButtons/>
    </div>

    <div className={"px-3"}>
      <QueryProviderAndToaster>
        {children}
      </QueryProviderAndToaster>
    </div>

    </body>
    </html>
  );
}
