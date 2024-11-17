import React from "react";
import {Badge} from "@/components/ui/badge";

export default function DateDisplay({ date }: { date: Date }) {
  const formattedDate = new Intl.DateTimeFormat("en-US", {
    year: "2-digit",
    month: "numeric",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    timeZoneName: "short", // Includes timezone info
    hour12: false
  }).format(date);

  return (
    <Badge variant={"secondary"}>{formattedDate}</Badge>
  );
};
