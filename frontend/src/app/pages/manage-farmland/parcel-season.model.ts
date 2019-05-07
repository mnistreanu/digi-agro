export class ParcelSeasonModel {
    id: number;
    parcelId: number;
    irrigated: any;
    parcelName: String;
    harvestYear: number;

    cropSeasonId: number;
    cropCategoryId: number;
    cropId: number;
    cropName: String;
    cropModel: any;
    cropSubcultureId: number;
    cropVarietyId: number;
    cropVarietyName: String;
    cropVarietyModel: any;
    yieldGoal: number;
    unitOfMeasure: String;

    plantedAt: Date;
    rowsOnParcel: number;
    plantsOnRow: number;
    spaceBetweenRows: number;
    spaceBetweenPlants: number;

}
