export class OtherWorksModel {
  parcelCropId: number;
  cropId: number;
  cropName:string;
  workType: string;
  unitOfMeasure: string;
  quantity: number;

  children: OtherWorksModel[];
}
