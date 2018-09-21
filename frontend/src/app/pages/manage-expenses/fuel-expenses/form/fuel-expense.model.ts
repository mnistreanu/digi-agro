import {ExpenseItemModel} from '../expense-item-table/expense-item.model';

export class FuelExpenseModel {
    id: number;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: ExpenseItemModel[] = [];
}

