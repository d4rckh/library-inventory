import React from "react";
import { Badge } from "@/components/ui/badge";

function formatDuration(seconds: number) {
  const days = Math.floor(seconds / 86400);
  const hours = Math.floor((seconds % 86400) / 3600);
  const minutes = Math.floor((seconds % 3600) / 60);
  const remainingSeconds = seconds % 60;

  return [
    days > 0 ? `${days}d` : null, // Include days if > 0
    hours > 0 || days > 0 ? `${hours}h` : null, // Include hours if > 0 or days exist
    minutes > 0 ? `${minutes}m` : "0m", // Always include minutes
    `${remainingSeconds}s`, // Always include seconds
  ]
    .filter(Boolean) // Remove null values
    .join(" ");
}

export default function Countdown({ targetDate }: { targetDate: Date }) {
  const now = Date.now(); // Current timestamp in milliseconds
  const targetTime = targetDate.getTime(); // Target timestamp in milliseconds
  const durationInSeconds = Math.max(0, Math.floor((targetTime - now) / 1000)); // Convert to seconds

  const formattedDuration = formatDuration(durationInSeconds);

  if (durationInSeconds == 0) return <Badge variant={"destructive"}>Late</Badge>;

  return <Badge variant={"secondary"}>{formattedDuration}</Badge>;
}
