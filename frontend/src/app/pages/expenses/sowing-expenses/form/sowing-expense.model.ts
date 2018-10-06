import {CropVarietyModel} from '../../../manage-crops/crop-variety/crop-variety.model';
import {CropModel} from '../../../manage-crops/crop/crop.model';

export class SowingExpenseModel {
    expenseId: number;
    expenseDate: Date;

    parcels: number[];

    cropModel: CropModel;
    cropVarietyModel: CropVarietyModel;
    cropAndVariety: string;
    unitOfMeasure: string;

    area: number;
    normSown1Ha: number;
    normSownTotal: number;

    actualSown1Ha: number;
    actualSownTotal: number;
    unitPrice: number;
    totalAmount: number;

    createdAt: Date;
    createdBy: string;
}

