import {LatLng} from '../../../interfaces/lat-lng.interface';

export class ParcelSeasonModel {
    id: number;
    // name: string;
    // cadasterNumber: string;
    // landWorthinessPoints: number;
    // area: number;
    // description: string;

    // coordinates: [number, number][];
    // paths: LatLng[];
    // fillColor: string;
    // center: LatLng;
    // icon: string;

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

    // lastWorkType: string;
    //
    // lastWorkDate: Date;
}
