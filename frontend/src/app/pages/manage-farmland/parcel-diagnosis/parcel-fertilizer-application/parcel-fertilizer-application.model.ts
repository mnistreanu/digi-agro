export class ParcelFertilizerApplicationModel {

    id: number;
    parcelId: number;
    applicationDate: Date;
    comments: string;
    placementType: number; // Broadcast, Seed Placed, Fertigate, Mid-Row Band, Injected, Side Band,
    // Dribble Band, Manure Spread, Aerial, Deep Band
    // applicationType: number;// Sol, Aer, In brazda,
    fertilizerType: number;
    fertilizerId: number;
    fertilizerName: string;
    tonePrice: number;
    fertilizedArea: number;
    rate: number;
    rateUnitOfMeasure: string;
    hectareCost: number;
}
