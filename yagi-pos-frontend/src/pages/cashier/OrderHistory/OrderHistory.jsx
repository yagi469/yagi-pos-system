import React from "react";
import OrderTable from "./OrderTable";
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import OrderDetails from "./OrderDetails/OrderDetails";
import { Button } from "@/components/ui/button";
import { Download } from "lucide-react";
import { useState } from "react";

const OrderHistory = () => {
    const [showOrderInvoiceDialog, setShowOrderInvoiceDialog] = useState(false);
    return (
        <div className="h-full flex flex-col">
            <div className="flex-1 overflow-auto">
                <OrderTable setShowOrderInvoiceDialog={setShowOrderInvoiceDialog} />
            </div>
            <Dialog open={showOrderInvoiceDialog} onOpenChange={setShowOrderInvoiceDialog}>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>Order - Invoice</DialogTitle>
                    </DialogHeader>
                    <OrderDetails />
                </DialogContent>
                <DialogFooter>
                    <Button>
                        <Download />
                        Download PDF
                    </Button>
                </DialogFooter>
            </Dialog>
        </div>
    );
};

export default OrderHistory;