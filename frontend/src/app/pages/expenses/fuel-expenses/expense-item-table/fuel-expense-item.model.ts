export class FuelExpenseItemModel {
    id: number;
    title: string;
    category: string;
    quantity: number;
    totalCost: number;

    deleted: boolean;
    readOnly: boolean;
}
