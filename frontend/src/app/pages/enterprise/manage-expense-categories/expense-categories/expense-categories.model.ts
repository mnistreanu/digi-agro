export class ExpenseCategoriesModel {

    id: number;
    parentId: number;
    defaultName: string;
    name: string;

    children: ExpenseCategoriesModel[];
}
