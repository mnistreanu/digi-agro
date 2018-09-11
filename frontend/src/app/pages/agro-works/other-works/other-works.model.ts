export class OtherWorksModel {

    id: number;

    parcelCropId: number;
    unitOfMeasure: string;
    quantity: number;

    cropId: number;
    cropNameRo: string;
    cropNameRu: string;
    cropName: string;

    workTypeId: number;
    workTypeNameRo: string;
    workTypeNameRu: string;
    workTypeName: string;

    children: OtherWorksModel[];
}
