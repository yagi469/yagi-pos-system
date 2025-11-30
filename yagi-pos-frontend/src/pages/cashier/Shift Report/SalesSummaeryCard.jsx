import { Card, CardContent } from "@/components/ui/card";
import React from "react";

const shiftData = {
    cashier: {
        fullName: "Pablo Panday"
    },
    shiftStart: "Aug 8, 2025 09:34 AM",
    shiftEnd: "Aug 8, 2025 05:34 PM",
    totalOrders: 59,
    totalSales: 69999,
    totalRefund: 21999,
    netSales: 48000,
}
const SalesSummaeryCard = () => {
    return (
        <Card className="my-4">
            <CardContent>
                <h2 className="text-xl font-semibold mb-4">Sales Summary</h2>

                <div className="space-y-2">
                    <div className="flex justify-between">
                        <span className="text-muted-foreground">Total Orders: </span>
                        <span className="font-medium">{shiftData.totalOrders}</span>
                    </div>
                    <div className="flex justify-between">
                        <span className="text-muted-foreground">Total Sales: </span>
                        <span className="font-medium">₹{shiftData.totalSales}</span>
                    </div>
                    <div className="flex justify-between">
                        <span className="text-muted-foreground">Total Refund: </span>
                        <span className="font-medium text-red-500">-₹{shiftData.totalRefund}</span>
                    </div>
                    <div className="flex justify-between border-t">
                        <span className="text-muted-foreground">Net Sales: </span>
                        <span className="font-medium">₹{shiftData.netSales}</span>
                    </div>
                </div>
            </CardContent>
        </Card>
    );
};

export default SalesSummaeryCard;