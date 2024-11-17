
export default function RootLayout({
                                     children,
                                   }: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>

      <div className={`max-w-[1800px] mx-auto pt-5`}>
        <h1 className={"text-4xl mb-4"}>Library Management</h1>
        {children}
      </div>
    </>
  );
}
