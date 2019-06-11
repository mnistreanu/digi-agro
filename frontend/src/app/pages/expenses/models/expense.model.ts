export class ExpenseModel {
    id: number;
    date: Date;

    cropSeasonId: number;

    categoryId: number;
    categoryName: string;
    subCategoryId: number;
    subCategoryName: string;

    title: string;
    description: string;
    cost: number;
}
