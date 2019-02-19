export class ParcelSeasonModel {
    id: number;
    parcelId: number;
    harvestYear: number;

    cropCategoryId: number;
    cropId: number;
    cropSubcultureId: number;
    cropVarietyId: number;
    yieldGoal: number;
    unitOfMeasure: String;

    plantedAt: Date;
    rowsOnParcel: number;
    plantsOnRow: number;
    spaceBetweenRows: number;
    spaceBetweenPlants: number;

}
