"use client";

import {Tabs, TabsContent, TabsList, TabsTrigger} from "@/components/ui/tabs";
import ReservationsDataTable from "@/components/reservations/ReservationsDataTable";
import {ReactNode} from "react";
import BorrowingsDataTable from "@/components/borrowings/BorrowingsDataTable";
import ItemsDataTable from "@/components/inventory/ItemsDataTable";
import CreateBookDialog from "@/components/books/CreateBookDialog";
import {Book, BookDashed, Gauge, Inbox, LibraryBig, Users} from "lucide-react";
import BooksDataTable from "@/components/books/BooksDataTable";
import UsersDataTable from "@/components/users/UsersDataTable";

export default function LibrarianDashboard({
  metrics
                                           }: {
  metrics: ReactNode;
}) {
  return <>
    <Tabs defaultValue={"dashboard"} className="w-full">
      <TabsList className={"mb-2"}>
        <TabsTrigger value="dashboard"><Gauge className={"w-4 mr-2"} /> Dashboard</TabsTrigger>
        <TabsTrigger value="users"><Users className={"w-4 mr-2"} /> Users</TabsTrigger>
        <TabsTrigger value="reservations"><Inbox className={"w-4 mr-2"} /> Reservations</TabsTrigger>
        <TabsTrigger value="borrows"><BookDashed className={"w-4 mr-2"} /> Borrows</TabsTrigger>
        <TabsTrigger value="inventory"><LibraryBig className={"w-4 mr-2"} /> Inventory</TabsTrigger>
        <TabsTrigger value="books"><Book className={"w-4 mr-2"} /> Books</TabsTrigger>
      </TabsList>
      <TabsContent value="dashboard">
        {metrics}

        <h1 className={"text-1xl mb-2"}>Quick actions</h1>
        <CreateBookDialog/>
      </TabsContent>
      <TabsContent value="users">
        <UsersDataTable/>
      </TabsContent>
      <TabsContent value="reservations">
        <ReservationsDataTable/>
      </TabsContent>
      <TabsContent value="borrows">
        <BorrowingsDataTable />
      </TabsContent>
      <TabsContent value="inventory">
        <ItemsDataTable />
      </TabsContent>
      <TabsContent value="books">
        <BooksDataTable />
      </TabsContent>
    </Tabs>
  </>;
}