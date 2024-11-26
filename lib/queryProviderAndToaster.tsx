"use client";

import {ReactNode} from "react";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {Toaster} from "@/components/ui/toaster";

const queryClient = new QueryClient()

export default function QueryProviderAndToaster({
    children
                                            }:{
    children: ReactNode
}) {
    return <>
        <QueryClientProvider client={queryClient}>
            {children}
        </QueryClientProvider>
        <Toaster />
    </>

}