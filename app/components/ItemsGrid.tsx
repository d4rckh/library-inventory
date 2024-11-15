import {Inventory} from "@/app/lib/actions/getInventoryByBook";
import ItemCard from "@/app/components/ItemCard";

export default function ItemsGrid({ items }: {
    items: Inventory[];
}) {
    return <div className={"grid grid-cols-3 mt-3 gap-3"}>
        {
            items.map(item =>
                <ItemCard item={item} key={item.id} />
            )
        }
    </div>
}