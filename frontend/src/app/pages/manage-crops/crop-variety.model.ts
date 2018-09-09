export class CropVarietyModel {


    cropCategoryId: number;
    cropId: number;
    id: number;

    nameRo: string;
    nameRu: string;
    descriptionRo: string;
    descriptionRu: string;

    children: CropVarietyModel[];
}
