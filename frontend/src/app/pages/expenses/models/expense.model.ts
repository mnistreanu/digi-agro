export class ExpenseModel {
    id: number;
    date: Date;

    cropSeasonId: number;

    categoryId: number;
    categoryName: string;
    categoryRootName: string;

    title: string;
    description: string;
    cost: number;
}
