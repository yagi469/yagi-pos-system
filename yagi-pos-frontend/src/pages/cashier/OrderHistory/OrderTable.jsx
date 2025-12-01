import React from 'react';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Button } from '@/components/ui/button';
import { EyeIcon } from 'lucide-react';
import { Printer } from 'lucide-react';

const orders = [
    {
        id: 1,
        createdAt: '2025-12-01 10:00:00',
        customer: {
            fullName: 'John Doe',
        },
        totalAmount: 100,
        paymentType: 'CASH',
        status: 'COMPLETED',
    },
    {
        id: 2,
        createdAt: '2025-12-02 11:00:00',
        customer: {
            fullName: 'Jane Smith',
        },
        paymentType: 'Card',
        totalAmount: 200,
    },
    {
        id: 3,
        createdAt: '2025-12-03 12:00:00',
        customer: {
            fullName: 'John Doe',
        },
        paymentType: 'CASH',
        totalAmount: 300,
        status: 'COMPLETED',
    },
];

const OrderTable = ({ setShowOrderInvoiceDialog }) => {
    return (
        <div>
            <h2 className="text-xl font-semibold mb-4">Recent Orders</h2>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-[150px]">Order ID</TableHead>
                        <TableHead className="w-[150px]">Date/Time</TableHead>
                        <TableHead className="w-[100px]">Customer</TableHead>
                        <TableHead className="w-[100px]">Amount</TableHead>
                        <TableHead className="w-[100px]">Payment Type</TableHead>
                        <TableHead className="w-[100px]">Status</TableHead>
                        <TableHead className="w-[100px]">Action</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {orders.map((order) => (
                        <TableRow key={order.id}>
                            <TableCell>{order.id}</TableCell>
                            <TableCell>{order.createdAt}</TableCell>
                            <TableCell>{order.customer?.fullName}</TableCell>
                            <TableCell>{order.paymentType}</TableCell>
                            <TableCell >₹{order.totalAmount}</TableCell>
                            <TableCell>{order.status}</TableCell>
                            <TableCell>
                                <div className="flex justify-end gap-2">
                                    <Button onClick={() => setShowOrderInvoiceDialog(true)} variant={"ghost"} size={"icon"}>
                                        <EyeIcon className='h-4 w-4' />
                                    </Button>

                                    <Button variant={"ghost"} size={"icon"}>
                                        <Printer className='h-4 w-4' />
                                    </Button>

                                    <Button variant={"ghost"} size={"icon"}>
                                        <EyeIcon className='h-4 w-4' />
                                    </Button>
                                </div>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </div>
    );
};

export default OrderTable;