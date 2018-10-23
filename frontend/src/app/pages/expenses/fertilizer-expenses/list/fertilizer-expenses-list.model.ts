import {FertilizerModel} from '../../../chemicals-pests/manage-fertilizers/fertilizer.model';
import {CropModel} from '../../../manage-crops/crop/crop.model';
export class FertilizerExpensesListModel {
  expenseDate: string;
  fertilizerModel: FertilizerModel;
  cropModel: CropModel;
  phase: string;
  result: string;
  comments: string;
}
