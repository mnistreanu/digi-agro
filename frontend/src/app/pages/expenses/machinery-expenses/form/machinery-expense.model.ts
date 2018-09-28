import {MachineryExpenseItemModel} from '../expense-item-table/machinery-expense-item.model';

export class MachineryExpenseModel {
    id: number;
    expenseCategoryId = 7;
    expenseSubCategoryId: number;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: MachineryExpenseItemModel[] = [];
}

