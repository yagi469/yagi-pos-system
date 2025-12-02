import React from 'react';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Button } from '@/components/ui/button';
import { EyeIcon } from 'lucide-react';
import { Printer } from 'lucide-react';

const orders = [
    {
        id: 1,
        createdAt: 'Jul 8, 2025, 12:37 PM',
        customer: {
            fullName: 'Pablo Pandy',
            phone: '1234567897',
        },
        totalAmount: 100,
        paymentType: 'CASH',
        status: 'COMPLETED',
        items: [
            {
                id: 1,
                product: {
                    image: 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
            {
                id: 2,
                product: {
                    image: 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
        ],
    },
    {
        id: 2,
        createdAt: 'Jul 8, 2025, 12:38 PM',
        customer: {
            fullName: 'Jane Smith',
            phone: '1234567898',
        },
        paymentType: 'Card',
        totalAmount: 200,
        items: [
            {
                id: 1,
                product: {
                    image: 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
            {
                id: 2,
                product: {
                    image: 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
        ],
    },
    {
        id: 3,
        createdAt: 'Jul 8, 2025, 12:39 PM',
        customer: {
            fullName: 'John Doe',
            phone: '1234567899',
        },
        paymentType: 'CASH',
        totalAmount: 300,
        status: 'COMPLETED',
        items: [
            {
                id: 1,
                product: {
                    image: 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
            {
                id: 2,
                product: {
                    image: 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=400&h=400&fit=crop',
                    name: 'men blue t-shirt',
                    sku: 'SHRT-S-COTTON-BLU-2025',
                    sellingPrice: 499,
                },
                quantity: 2,
            },
        ],
    },
];

const OrderTable = () => {
    return (
        <div className="flex-1 overflow-auto p-4">
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead>Order ID</TableHead>
                        <TableHead>Date/Time</TableHead>
                        <TableHead>Customer</TableHead>
                        <TableHead>Amount</TableHead>
                        <TableHead>Payment Type</TableHead>
                        <TableHead>Status</TableHead>
                        <TableHead className="text-right">Action</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {orders.map((order) => (
                        <TableRow key={order.id}>
                            <TableCell>{order.id}</TableCell>
                            <TableCell>{order.createdAt}</TableCell>
                            <TableCell>{order.customer?.fullName}</TableCell>
                            <TableCell>{order.totalAmount}</TableCell>
                            <TableCell>{order.paymentType}</TableCell>
                            <TableCell>{order.status}</TableCell>
                            <TableCell>
                                <div className="flex justify-end gap-2">
                                    <Button variant={"ghost"} size={"icon"}>
                                        <EyeIcon className='h-4 w-4' />
                                    </Button>

                                    <Button variant={"ghost"} size={"icon"}>
                                        <Printer className='h-4 w-4' />
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