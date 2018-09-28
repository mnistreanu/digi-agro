
import {MachineryExpenseItemModel} from '../machinery-expenses/expense-item-table/machinery-expense-item.model';
export class ExpenseModel {
    id: number;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: MachineryExpenseItemModel[] = [];
}

