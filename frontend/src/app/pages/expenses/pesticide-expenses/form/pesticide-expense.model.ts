import {PesticideExpenseItemModel} from '../expense-item-table/pesticide-expense-item.model';

export class PesticideExpenseModel {
    id: number;
    categoryId = 4;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: PesticideExpenseItemModel[] = [];
}

