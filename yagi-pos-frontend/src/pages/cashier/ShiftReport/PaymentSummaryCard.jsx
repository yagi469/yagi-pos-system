import { Card, CardContent } from "@/components/ui/card";
import { CreditCard } from "lucide-react";
import React from "react";

const shiftData = {
    paymentSummaries: [
        {
            type: "CASH",
            totalAmount: 2999,
            transactionCount: 6
        },
        {
            type: "CARD",
            totalAmount: 3499,
            transactionCount: 8
        },
        {
            type: "UPI",
            totalAmount: 48499,
            transactionCount: 39
        },
    ],
    totalSales: 69999,
}

const PaymentSummaryCard = () => {
    return (
        <Card>
            <CardContent>
                <h2 className="text-xl font-semibold mb-4">Payment Summary</h2>

                <div className="space-y-4">
                    {
                        shiftData.paymentSummaries.map((payment) => (
                            <div className="flex items-center" key={payment.type}>
                                <div className="w-10 h-10 rounded-full flex bg-primary/10 flex
                                 items-center justify-center mr-4">
                                    <CreditCard />
                                </div>
                                <div className="flex-1">
                                    <div className="flex justify-between">
                                        <span className="font-medium">{payment.type}</span>
                                        <span className="font-bold">₹{payment.totalAmount}</span>
                                    </div>
                                    <div className="flex justify-between text-sm text-muted-foreground">
                                        <span className="">{payment.transactionCount} transactions</span>
                                        <span className="font-medium">{((payment.totalAmount / shiftData.totalSales) * 100).toFixed(1)}%</span>
                                    </div>
                                </div>
                            </div>
                        ))
                    }
                </div>
            </CardContent>
        </Card>
    );
};

export default PaymentSummaryCard;
