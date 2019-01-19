export class WorksExpenseModel {
    expenseId: number;
    expenseDate: Date;

    machines: number[];
    employees: number[];
    parcels: number[];

    workTypeId: number;
    cropCategoryId: number;
    cropId: number;

    unitOfMeasure: string;
    quantity: number;
    quantityNorm: number;
    quantityDefacto: number;
    price1Norm: number;
    sum: number;

    createdAt: Date;
    createdBy: string;
}

