export class ExpenseCategoryTreeModel {

    id: number;
    parentId: number;
    name: string;

    children: ExpenseCategoryTreeModel[];
}
