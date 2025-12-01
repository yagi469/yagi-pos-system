import React from "react";

const shiftData = {
    recentOrders: [
        {
            id: 1,
            createdAt: "01:25 PM",
            paymentType: "CASH",
            totalAmount: 7899
        },
        {
            id: 2,
            createdAt: "01:25 PM",
            paymentType: "CASH",
            totalAmount: 2000,
        },
        {
            id: 3,
            createdAt: "01:25 PM",
            paymentType: "CASH",
            totalAmount: 1200,
        }
    ]
}

const RecentOrdersTable = () => {
    return (
        <div>
            <h1>Recent Orders Table</h1>
        </div>
    );
};

export default RecentOrdersTable;
