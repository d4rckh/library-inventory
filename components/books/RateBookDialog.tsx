"use client";

import {useBook, useLoggedUser, useRatings} from "@/lib/queries/items";
import {
  Dialog, DialogClose,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";
import StarRatingSelect from "@/components/StarRatingSelect";
import {useEffect, useState} from "react";
import {createRating} from "@/app/lib/actions/createRating";
import {useToast} from "@/hooks/use-toast";
import {useQueryClient} from "@tanstack/react-query";

export default function RateBookDialog({ bookId }: { bookId: number }) {
  const {data: book, isSuccess: fetchedBook} = useBook(bookId);
  const {data: user} = useLoggedUser();

  const {data: ratings} = useRatings({ userId: user?.id, bookId }, !!user && !!book);

  const [ratingValue, setRatingValue] = useState(0);

  const {toast} = useToast();
  const query = useQueryClient();

  useEffect(() => {
    if (ratings && ratings.length >= 1)
      setRatingValue(ratings[0].rating)
    else if (ratings && ratings.length == 0)
      setRatingValue(0);
  }, [ratings]);

  if (!fetchedBook || book == null || !user) return <></>;

  return <>
    <Dialog>
      <DialogTrigger asChild>
        <Button size={"sm"}>Rate book</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Rate {book.title}</DialogTitle>
          <DialogDescription>You can only rate books if you have borrowed and returned them</DialogDescription>
        </DialogHeader>
        <StarRatingSelect isClickable={true} value={ratingValue} setValue={setRatingValue} />
        <DialogClose asChild>
          <Button onClick={async () => {
            const rating = await createRating({
              userId: user.id, bookId, rating: ratingValue
            });

            await query.invalidateQueries({ queryKey: ["ratings"] })

            toast({
              title: rating.error ? "Failed to rate book" : "Successfully rated this book",
              description: rating.error ? rating.error.message : ""
            });
          }}>Save</Button>
        </DialogClose>
      </DialogContent>
    </Dialog>
  </>
}