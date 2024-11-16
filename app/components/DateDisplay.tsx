import React from "react";
import {Badge} from "@/components/ui/badge";

export default function DateDisplay({ date }: { date: Date }) {

  const formattedDate = new Intl.DateTimeFormat("en-US", {
    year: "numeric",
    month: "numeric",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    timeZoneName: "short", // Includes timezone info
  }).format(date);

  return (
    <Badge variant={"secondary"}>{formattedDate}</Badge>
  );
};
