import "../globals.css";
import Link from "next/link";
import {Button} from "@/components/ui/button";

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
        </div>

        <div className={"px-3"}>
            {children}
        </div>

        </body>
        </html>
    );
}
