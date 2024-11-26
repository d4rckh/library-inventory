"use client"

import {Button} from "@/components/ui/button";
import {createReservation} from "@/app/lib/actions/createReservation";
import {useQueryClient} from "@tanstack/react-query";
import {useToast} from "@/hooks/use-toast";
import {BookOpen} from "lucide-react";

export default function ReserveItemButton({itemId}: { itemId: number }) {
  const query = useQueryClient();
  const { toast } = useToast();

  return <Button size={"sm"} onClick={() => {
    createReservation(itemId).then(async r => {
      if (!r.error) {
        await query.invalidateQueries({ queryKey: ["reservations", "list"] });
        await query.invalidateQueries({ queryKey: ["borrowings", "list"] });
        await query.invalidateQueries({ queryKey: ["items", "list"] });
      }

      toast({
        title: r.error ? "Failed to reserve this item" : "Successfully reserved this item",
        description: r.error ? r.error.message : ""
      });
    });
  }}>
    <BookOpen /> Reserve now
  </Button>
}