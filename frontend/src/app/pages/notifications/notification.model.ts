export class NotificationModel {

    id: number;
    userId: number;
    typeId: number;
    translationKey: string;
    type: string;
    message:string;
    dateTo: Date;
    durationDays;
    durationHours;
    seenAt: Date;

    notificationImage: string;
}