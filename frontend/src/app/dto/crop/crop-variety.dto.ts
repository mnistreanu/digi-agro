export class CropVarietyDTO {
    constructor(
        public id: number,
        public cropId: number,
        public nameRo: String,
        public nameRu: String,
        public descriptionRo: String,
        public descriptionRu: String,
        public seedConsumptionHa: number,
        public unitOfMeasure: String) { }
}