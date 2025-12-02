import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { ChevronLeftIcon } from 'lucide-react';
import React from 'react';

const OrderDetailsSection = ({ handleSelectOrder, selectedOrder }) => {
    return (
        <div className='w-1/2 border-r p-4'>
            <div className='mb-4'>
                <Button onClick={() => { handleSelectOrder(null) }}>
                    <ChevronLeftIcon />
                    back to order table
                </Button>
            </div>
            <Card>
                <CardContent>
                    <div className='flex justify-between items-start mb-4'>
                        <div>
                            <h2 className='font-semibold text-lg'>Order {selectedOrder.id}</h2>
                            <p>{selectedOrder.createdAt}</p>
                        </div>
                        <Badge variant="outline">
                            {selectedOrder.paymentType}
                        </Badge>
                    </div>
                </CardContent>
            </Card>
        </div>
    );
};

export default OrderDetailsSection;