export class ExpenseCategoryTreeModel {

    id: number;
    parentId: number;
    defaultName: string;
    name: string;

    children: ExpenseCategoryTreeModel[];
}
