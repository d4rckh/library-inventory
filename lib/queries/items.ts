import {createQueryKeys} from "@lukemorales/query-key-factory";
import {getItems, InventoryFilter} from "@/app/lib/actions/getItems";

export const items = createQueryKeys("items", {
  list: (filters: InventoryFilter) => ({
    queryKey: [filters],
    queryFn: (filters: InventoryFilter) => getItems(filters),
  })
});
