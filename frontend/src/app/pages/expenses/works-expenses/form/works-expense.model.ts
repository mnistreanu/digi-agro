import {WorksExpenseItemModel} from '../expense-item-table/works-expense-item.model';

export class WorksExpenseModel {
    id: number;
    categoryId = 4;
    title: string;
    expenseDate: Date;

    machines: number[];
    employees: number[];

    expenseItems: WorksExpenseItemModel[] = [];
}

