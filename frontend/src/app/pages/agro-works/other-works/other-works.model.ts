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

    am: number;
    ns: number;
    zm: number;
    om: number;
    suma: number;
    prod: number;
    petrol: number;
    ulei: number;


    children: OtherWorksModel[];
}
