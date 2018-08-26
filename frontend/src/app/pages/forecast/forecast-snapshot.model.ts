import {ForecastHarvestModel} from './forecast-harvest.model';

export class ForecastSnapshotModel {
    id: number;
    unitOfMeasure: string;
    currency: string;
    unitPrice: number;
    parcels: number[];

    harvests: ForecastHarvestModel[];
}
