import {Component, ViewEncapsulation} from '@angular/core';

import {MessagesService} from '../../../services/messages.service';
import {ReminderService} from '../../../services/reminder.service';
import {ReminderModel} from "../../../pages/reminder/reminder.model";
import {NotificationModel} from "../../../pages/notifications/notification.model";
import {NotificationService} from "../../../services/notification/notification.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
    selector: 'az-messages',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./messages.component.scss'],
    templateUrl: './messages.component.html',
    providers: [MessagesService, NotificationService, ReminderService, TranslateService]
})

export class MessagesComponent{     
    public messages:Array<Object> = [];

    public notifications:Array<Object> = [];
    public notificationModels: NotificationModel[];

    public reminders:Array<Object> = [];
    public reminderModels: ReminderModel[];

    constructor (private messagesService:MessagesService, private reminderService:ReminderService,
                 private notificationService: NotificationService, private translate: TranslateService){
        this.messages = messagesService.getMessages();
        this.findNotifications();
        this.findReminders();
    }

    private findReminders() {
        // task.text: 'Design some buttons',
        // task.value: '20%',
        // task.class: 'info'

        this.reminderService.find().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.reminderModels = payloadModel.payload || [];

            this.reminderModels.forEach((model) => {
                let agroTask: any = {};
                agroTask.id = model.id;
                agroTask.workTypeId = model.workTypeId;
                agroTask.text = model.title;
                agroTask.value = '20%';
                agroTask.class = this.getTaskClass(agroTask.workTypeId);
                this.reminders.push(agroTask);
            });

        });
    }

    private getTaskClass(workTypeId) {
        switch (workTypeId) {
            case 1:
                return 'success';
            case 2:
                return 'primary';
            case 3:
                return 'warning';
            case 4:
                return 'danger';
            case 5:
                return 'info';
            case 6:
                return 'dark';
            default:
                return 'gray';
        }
    }


    private findNotifications() {
        // name: 'michael',
        // text: 'Michael posted a new article.',
        // time: '1 min ago'

        this.notificationService.find().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.notificationModels = payloadModel.payload || [];

            this.notificationModels.forEach((model) => {
                let notification: any = {};
                notification.id = model.id;
                notification.typeId = model.typeId;
                notification.name = model.translationKey;
                notification.text = model.message;
                notification.time = 'peste ' + model.durationDays + ' zile si ' + model.durationHours +' ore'; //TODO de facut corect
                notification.image = '../assets/img/notifications/'+model.translationKey+ '.png';
                this.notifications.push(notification);
            });

        });
    }
}