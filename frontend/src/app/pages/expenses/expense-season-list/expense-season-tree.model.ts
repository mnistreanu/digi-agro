import {CropSeasonListModel} from '../../manage-crops/crop-season/list/crop-season-list.model';
export class ExpenseSeasonTreeModel {

    cropSeasonModel: CropSeasonListModel;
    categoryName: String;

    // combination cropSeason and expenseCategory
    title: String;

    // key: periodIndex, value: cost
    values: Map<number, number>;
    totalCost: number;

    children: ExpenseSeasonTreeModel[] = [];
}
