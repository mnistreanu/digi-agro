import {CropModel} from '../crop/crop.model';
export class CropVarietyModel {
        public id: number;
        public cropCategoryId: number;
        public cropModel: CropModel;
        public cropId: number;
        public cropSubcultureId: number;
        public nameRo: String;
        public nameRu: String;
        public descriptionRo: String;
        public descriptionRu: String;
}
