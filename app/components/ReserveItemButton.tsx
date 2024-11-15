"use client"

import {Button} from "@/components/ui/button";
import {createReservation} from "@/app/lib/actions/createReservation";

export default function ReserveItemButton({ itemId }: { itemId: number }) {
  return <Button onClick={() => {
    createReservation(itemId).then(r => {
      if (!r.error) alert("Item reserved successfully");
      else alert(r.error.message)
    });
  }}>
    Reserve Item

  </Button>
}