export class ReminderModel {

    id: number;
    tenantId: number;
    workTypeId: number;
    title: string;
    description: string;
    starting: Date;
    ending: Date;
    createdBy: number;
}
