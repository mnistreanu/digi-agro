export class ParcelFertilizerApplicationModel {

    id: number;
    parcelId: number;
    applicationDate: Date;
    comments: string;
    placementType: number; // Sol, Aer, In brazda,
    fertilizerType: number;
    fertilizerId: number;
    fertilizerName: string;
    tonePrice: number;
    fertilizedArea: number;
    rate: number;
    rateUnitOfMeasure: string;
    hectareCost: number;
}
