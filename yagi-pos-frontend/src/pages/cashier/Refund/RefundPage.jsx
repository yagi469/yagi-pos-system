import React from 'react';
import OrderTable from './OrderTable';
import OrderDetailsSection from './OrderDetailsSection';
import ReturnItemSection from './ReturnItemSection';
import { useState } from 'react';
import ReturnReceiptDialog from './ReturnReceiptDialog';

const RefundPage = () => {
    const [selectedOrder, setSelectedOrder] = useState(null);
    const handleSelectOrder = (order) => {
        setSelectedOrder(order);
    };
    return (
        <div className='h-full flex flex-col'>
            <div className='p-4 bg-card border-b'>
                <h1 className='text-2xl font-bold'>Return/Refund</h1>
            </div>
            <div className='flex-1 flex overflow-hidden'>
                {!selectedOrder ? (
                    <OrderTable handleSelectOrder={handleSelectOrder} />
                ) : (
                    <>
                        <OrderDetailsSection handleSelectOrder={handleSelectOrder} selectedOrder={selectedOrder} />
                        <ReturnItemSection />
                    </>
                )}
            </div>
            {selectedOrder && <ReturnReceiptDialog />}
        </div>
    );
};

export default RefundPage;