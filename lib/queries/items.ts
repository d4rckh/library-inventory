import {BookFilters, getBooks} from "@/app/lib/actions/getBooks";
import {useQuery} from "@tanstack/react-query";
import {getItems, InventoryFilter} from "@/app/lib/actions/getItems";
import {BorrowingFilters, getBorrowings} from "@/app/lib/actions/getBorrowings";
import {getReservations, ReservationFilters} from "@/app/lib/actions/getReservations";
import fetchApi from "@/app/lib/fetchApi";
import {getTags} from "@/app/lib/actions/getTags";

export const useItems = (filters?: InventoryFilter) => {
  return useQuery({
    queryKey: ["items", "list", filters],
    queryFn: () => getItems(filters),
  })
}

export const useBooks = (filters?: BookFilters) => {
  return useQuery({
    queryKey: ["books", "list", filters],
    queryFn: () => getBooks(filters),
  })
}

export const useBorrowings = (filters?: BorrowingFilters) => {
  return useQuery({
    queryKey: ["borrowings", "list", filters],
    queryFn: () => getBorrowings(filters),
  })
}

export const useReservations = (filters?: ReservationFilters) => {
  return useQuery({
    queryKey: ["reservations", "list", filters],
    queryFn: () => getReservations(filters),
  })
}

export const useTags = () => {
  return useQuery({
    queryKey: ["tags", "list"],
    queryFn: () => getTags(),
  })
}
