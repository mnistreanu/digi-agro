export class ParcelModel {

    id: number;
    name: string;
    cadasterNumber: string;
    landWorthinessPoints: number;
    area: number;
    irrigated: boolean;
    description: string;
    coordinates: any;

    paths: any[];
    fillColor: string;
    center: any;
    icon: string;

    cropName: string;
    cropNameRo: string;
    cropNameRu: string;

    plantedAt: Date;
    rowsOnParcel: number;
    plantsOnRow: number;
    spaceBetweenRows: number;
    spaceBetweenPlants: number;

    lastWorkType: string;
    lastWorkTypeRo: string;
    lastWorkTypeRu: string;

    lastWorkDate: Date;
}
