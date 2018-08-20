import {ForecastSnapshotModel} from './forecast-snapshot.model';

export class ForecastModel {

    id: number;
    name: string;
    description: string;
    harvestingYear: number;

    cropCategoryId: number;
    cropId: number;
    cropVarietyId: number;

    snapshot: ForecastSnapshotModel;
}
