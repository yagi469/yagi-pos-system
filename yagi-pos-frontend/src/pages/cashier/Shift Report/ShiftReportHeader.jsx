import { ArrowRight } from "lucide-react";
import React from "react";
import { Button } from "@/components/ui/button";


const ShiftReportHeader = () => {
    return (
        <div className="p-4 bg-card border-b">
            <div className="flex items-center justify-between">
                <h1 className="text-2xl font-bold">Shift Summary</h1>
                <div className="flex gap-2">
                    <Button variant={"destructive"}>
                        <ArrowRight />
                        End Shift & Logout
                    </Button>
                </div>
            </div>
        </div>
    );
};

export default ShiftReportHeader;