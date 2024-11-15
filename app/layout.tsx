import "./globals.css";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`max-w-5xl mx-auto pt-5`}
      >
        <h1 className={"text-4xl mb-5 text-center"}>Library</h1>
        {children}
      </body>
    </html>
  );
}
