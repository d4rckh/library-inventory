import "../../globals.css";

export default function Layout({
                                     children,
                                   }: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className={"px-3"}>
      <h1 className={"text-3xl mb-2 mt-2"}>Library Management</h1>

      {children}
    </div>
  );
}
