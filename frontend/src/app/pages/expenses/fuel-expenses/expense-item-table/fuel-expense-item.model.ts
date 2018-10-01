export class FuelExpenseItemModel {
    id: number;
    title: string;
    category: string;
    categoryId: number;
    quantity: number;
    totalCost: number;

    deleted: boolean;
    readOnly: boolean;
}
