export class AgroTaskModel {

    id: number;
    tenantId: number;
    workTypeId: number;
    title:string;
    description:string;
    scheduledStart:Date;
    scheduledEnd:Date;
    createdBy: number;
}