export class NotificationModel {

    id: number;
    userId: number;
    typeId: number;
    translationKey: string;
    message:string;
    dateTo:Date;
    durationDays;
    durationHours;
    seenAt:Date;
}