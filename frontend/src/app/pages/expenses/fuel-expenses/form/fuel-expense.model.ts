import {FuelExpenseItemModel} from '../expense-item-table/fuel-expense-item.model';

export class FuelExpenseModel {
    id: number;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: FuelExpenseItemModel[] = [];
}

