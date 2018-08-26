export class ParcelModel {

    id: number;
    name: string;
    cadasterNumber: string;
    landWorthinessPoints: number;
    area: number;
    description: string;
    coordinates: any;

    paths: any[];
    fillColor: string;
    center: any;
    icon: string;

    cropNameRo: string;
    cropNameRu: string;

    plantedAt: Date;
    rowsOnParcel: number;
    plantsOnRow: number;
    spaceBetweenRows: number;
    spaceBetweenPlants: number;

    lastWorkTypeRo: string;
    lastWorkTypeRu: string;
    lastWorkDate: Date;
}
