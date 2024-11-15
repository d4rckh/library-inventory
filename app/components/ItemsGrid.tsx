import {Card, CardHeader, CardTitle} from "@/components/ui/card";
import {Inventory} from "@/app/lib/actions/getInventoryByBook";

export default function ItemsGrid({ items }: {
    items: Inventory[];
}) {
    return <div className={"grid grid-cols-3 mt-3 gap-3"}>
        {
            items.map(item =>
                <Card key={item.id}>
                    <CardHeader>
                        <CardTitle>ID: {item.id}</CardTitle>
                    </CardHeader>
                </Card>
            )
        }
    </div>
}