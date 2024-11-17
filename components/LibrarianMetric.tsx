import {Card, CardHeader} from "@/components/ui/card";

export default function LibrarianMetric({ value, title }: { value: number, title: string }) {
  return <Card>
    <CardHeader className={"flex flex-col"}>
      <span className={"text-2xl"}>{value}</span>
      <span className={"text-gray-500 dark:text-muted"}>{title}</span>
    </CardHeader>
  </Card>

}