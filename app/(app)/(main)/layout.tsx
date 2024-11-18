"use client";

import {ReactNode} from "react";

export default function RootLayout({
  children,
}: Readonly<{
  children: ReactNode;
  }>) {
  return (
  <div className={`max-w-7xl mx-auto pt-5`}>
    {children}
  </div>
);
}
