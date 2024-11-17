import {Tabs, TabsContent, TabsList, TabsTrigger} from "@/components/ui/tabs";
import ReservationsDataTable from "@/app/components/ReservationsDataTable";
import {ReactNode} from "react";

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
      </TabsList>
      <TabsContent value="dashboard">
        {metrics}
      </TabsContent>
      <TabsContent value="reservations">
        <ReservationsDataTable />
      </TabsContent>
    </Tabs>
  </>;
}