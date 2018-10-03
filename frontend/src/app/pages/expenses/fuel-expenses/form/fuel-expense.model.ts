import {FuelExpenseItemModel} from '../expense-item-table/fuel-expense-item.model';

export class FuelExpenseModel {
    id: number;
    categoryId = 2;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: FuelExpenseItemModel[] = [];
}

