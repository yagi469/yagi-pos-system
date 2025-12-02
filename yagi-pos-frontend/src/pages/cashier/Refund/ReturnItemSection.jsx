import { Card, CardContent } from '@/components/ui/card';
import { Label } from '@/components/ui/label';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import React, { useState } from 'react';

const returnReasons = [
    "Wrong product",
    "Damage product",
    "Not interested any more",
    "Other"
]

const ReturnItemSection = () => {
    const [returnReason, setReturnReason] = useState("");
    return (
        <div className='p-4 w-1/2'>
            <Card className={"mt-4"}>
                <CardContent className={"p-4"}>
                    <div className='space-y-4'>
                        <div>
                            <Label className={"mb-2 block"}>
                                Return Reason
                            </Label>
                            <Select value={returnReason}
                                onValueChange={(value) => setReturnReason(value)}>
                                <SelectTrigger>
                                    <SelectValue placeholder="Select A Reason..." />
                                </SelectTrigger>
                                <SelectContent>
                                    {returnReasons.map((reason) => (
                                        <SelectItem key={reason} value={reason}>
                                            {reason}
                                        </SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>
    );
};

export default ReturnItemSection;