export class NotificationModel {

    id: number;
    userId: number;
    notificationTypeId: number;
    message:string;
    createdAt:Date;
    seenAt:Date;
}