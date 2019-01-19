import {CropModel} from '../../crop/crop.model';
export class CropSeasonModel {
        public id: number;
        public cropCategoryId: number;
        public cropModel: CropModel;
        public cropId: number;
        public nameRo: String;
        public nameRu: String;
        public descriptionRo: String;
        public descriptionRu: String;
        public seedConsumptionHa: number;
        public unitOfMeasure: String;
}