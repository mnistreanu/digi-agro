import {FertilizerExpenseItemModel} from '../expense-item-table/fertilizer-expense-item.model';

export class FertilizerExpenseModel {
    id: number;
    categoryId = 3;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: FertilizerExpenseItemModel[] = [];
}

