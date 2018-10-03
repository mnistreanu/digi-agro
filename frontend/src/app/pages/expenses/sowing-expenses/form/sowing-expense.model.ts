import {SowingExpenseItemModel} from '../expense-item-table/sowing-expense-item.model';

export class SowingExpenseModel {
    id: number;
    categoryId = 3;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: SowingExpenseItemModel[] = [];
}

