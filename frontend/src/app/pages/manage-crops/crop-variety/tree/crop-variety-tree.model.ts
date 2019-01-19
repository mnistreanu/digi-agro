export class CropVarietyTreeModel {

    cropCategoryId: number;
    cropId: number;
    cropVarietyId: number;

    nameRo: string;
    nameRu: string;
    descriptionRo: string;
    descriptionRu: string;

    children: CropVarietyTreeModel[];
}
