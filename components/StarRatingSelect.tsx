import {Star} from "lucide-react";

export function SingleStar({
                             number,
                             currentRating,
                             isClickable,
                             updateRating
                           }: {
  number: number,
  currentRating: number,
  isClickable: boolean,
  updateRating?: (rating: number) => void
}) {
  const isFilled = currentRating >= number;
  const handleClick = () => updateRating && updateRating(currentRating === number ? 0 : number);

  return (
    <Star
      className={`w-5 ${isFilled ? "fill-black" : ""} ${isClickable ? "hover:scale-125 duration-200" : ""}`}
      onClick={isClickable ? handleClick : undefined}
    />
  );
}

export default function StarRatingSelect({ className, value, setValue, isClickable }: { className?: string, isClickable: boolean, value: number, setValue?: (value: number) => void }) {
  const stars = [1, 2, 3, 4, 5];

  return (
    <span className={`flex flex-row gap-1 select-none ${className}`}>
      {stars.map((star) => (
        <SingleStar
          key={star}
          number={star}
          currentRating={value}
          isClickable={isClickable}
          updateRating={setValue}
        />
      ))}
    </span>
  );
}
