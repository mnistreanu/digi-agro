export class ExpenseCategoryModel {

    id: number;
    parentId: number;

    rootName: string;
    name: string;
    description: string;

    children: ExpenseCategoryModel[];
}
