import {ExpenseItemModel} from '../expense-item-table/expense-item.model';

export class MachineryExpenseModel {
    expenseId: number;
    expenseTitle: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: ExpenseItemModel[] = [];
}

