export class SowingExpenseModel {
    expenseId: number;
    expenseDate: Date;

    parcels: number[];

    cropModel: any;
    cropVarietyModel: any;
    cropAndVariety: string;
    unitOfMeasure: string;

    area: number;
    normSown1Ha: number;
    normSownTotal: number;

    actualSown1Ha: number;
    actualSownTotal: number;
    unitPrice: number;
    totalAmount: number;

    createdAt: Date;
    createdBy: string;
}

