"use client";

import {Tabs, TabsContent, TabsList, TabsTrigger} from "@/components/ui/tabs";
import ReservationsDataTable from "@/app/components/ReservationsDataTable";
import {ReactNode} from "react";
import BorrowingsDataTable from "@/app/components/BorrowingsDataTable";
import ItemsDataTable from "@/app/components/ItemsDataTable";

export default function LibrarianDashboard({
  metrics
                                           }: {
  metrics: ReactNode;
}) {
  return <>
    <Tabs defaultValue={"dashboard"} className="w-full">
      <TabsList className={"mb-2"}>
        <TabsTrigger value="dashboard">Dashboard</TabsTrigger>
        <TabsTrigger value="reservations">Reservations</TabsTrigger>
        <TabsTrigger value="borrows">Borrows</TabsTrigger>
        <TabsTrigger value="inventory">Inventory</TabsTrigger>
      </TabsList>
      <TabsContent value="dashboard">
        {metrics}
      </TabsContent>
      <TabsContent value="reservations">
        <ReservationsDataTable />
      </TabsContent>
      <TabsContent value="borrows">
        <BorrowingsDataTable />
      </TabsContent>
      <TabsContent value="inventory">
        <ItemsDataTable />
      </TabsContent>
    </Tabs>
  </>;
}