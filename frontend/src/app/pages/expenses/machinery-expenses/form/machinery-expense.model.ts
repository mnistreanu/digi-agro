import {MachineryExpenseItemModel} from '../expense-item-table/machinery-expense-item.model';

export class MachineryExpenseModel {
    id: number;
    categoryId = 1;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: MachineryExpenseItemModel[] = [];
}

