"use client";

import {useStats} from "@/lib/queries/items";
import {
  ChartContainer,
  ChartLegend,
  ChartLegendContent,
  ChartTooltip,
  ChartTooltipContent
} from "@/components/ui/chart";
import {Area, AreaChart, CartesianGrid, XAxis, YAxis} from "recharts";
import {Card, CardContent} from "@/components/ui/card";

export default function LibrarianCharts() {

  const { data, isSuccess } = useStats();

  if (!isSuccess) return <>Error loading stats...</>;

  return <Card className={"w-full h-[300px] mb-2 p-0"}>
    <CardContent className={"h-full w-full pr-2 pt-2 pb-2"}>
      <ChartContainer config={({
        dailyBorrowings: { label: "Borrowings", color: "hsl(var(--chart-1))" },
        dailyReservations: { label: "Reservations", color: "hsl(var(--chart-2))" },
        dailyReturns: { label: "Returns", color: "hsl(var(--chart-3))" },
        dailyUsersRegistered: {label: "New users", color: "hsl(var(--chart-4))" },
      })} className="h-full w-full">
        <AreaChart accessibilityLayer data={data}>
          <ChartTooltip
            cursor={false}
            content={<ChartTooltipContent indicator="dot" />}
          />
          <XAxis
            dataKey="statDate"
            tickLine={false}
            axisLine={false}
            tickMargin={8}
          />
          <YAxis
            tickLine={false}
            axisLine={false}
            tickCount={5}
          />
          <CartesianGrid vertical={false} />
          <Area dataKey={"dailyBorrowings"} fill={"var(--color-dailyBorrowings)"} stroke={"var(--color-dailyBorrowings)"} />
          <Area dataKey={"dailyReservations"} fill={"var(--color-dailyReservations)"} stroke={"var(--color-dailyReservations)"} />
          <Area dataKey={"dailyReturns"} fill={"var(--color-dailyReturns)"} stroke={"var(--color-dailyReturns)"} />
          <Area dataKey={"dailyUsersRegistered"} fill={"var(--color-dailyUsersRegistered)"} stroke={"var(--color-dailyUsersRegistered)"} />
          <ChartLegend content={<ChartLegendContent />} />
        </AreaChart>
      </ChartContainer>
    </CardContent>
  </Card>

}