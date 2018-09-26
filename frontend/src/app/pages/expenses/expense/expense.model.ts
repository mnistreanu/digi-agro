
import {ExpenseItemModel} from '../machinery-expenses/expense-item-table/expense-item.model';
export class ExpenseModel {
    id: number;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: ExpenseItemModel[] = [];
}

