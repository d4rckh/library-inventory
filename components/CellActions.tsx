import {ReactNode} from "react";

export default function CellActions({ children }: { children: ReactNode }) {
  return <div className={"flex flex-row gap-1"}>
    {children}
  </div>;
}