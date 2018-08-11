import {Component, ViewEncapsulation} from '@angular/core';

import {MessagesService} from '../../../services/messages.service';
import {ReminderService} from '../../../services/reminder.service';
import {ReminderModel} from '../../../pages/reminder/reminder.model';
import {NotificationModel} from '../../../pages/notifications/notification.model';
import {NotificationService} from '../../../services/notification/notification.service';
import {TranslateService} from '@ngx-translate/core';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-messages',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./messages.component.scss'],
    templateUrl: './messages.component.html',
    providers: [MessagesService, NotificationService, ReminderService, TranslateService]
})

export class MessagesComponent {
    public messages: Array<Object> = [];

    public hasNotifications = false;
    public notificationModels: NotificationModel[] = [];

    public reminders: Array<Object> = [];
    public reminderModels: ReminderModel[];

    constructor(private messagesService: MessagesService,
                private reminderService: ReminderService,
                private notificationService: NotificationService,
                private langService: LangService) {
        this.messages = messagesService.getMessages();
        this.findNotifications();
        this.findReminders();
    }

    private findReminders() {
        // task.text: 'Design some buttons',
        // task.value: '20%',
        // task.class: 'info'

        this.reminderService.find().subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.reminderModels = payloadModel.payload || [];

            this.reminderModels.forEach((model) => {
                const agroTask: any = {};
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
        this.notificationService.find().subscribe(models => {
            this.notificationModels = models;
            this.hasNotifications = models.length > 0;
            this.notificationModels.forEach((model) => {
                this.langService.get('notification.remaining-time', {days: model.durationDays, hours: model.durationHours})
                    .subscribe(msg => model.remainingTime = msg);
                model.image = '../assets/img/notifications/' + model.translationKey + '.png';
            });
        });
    }

    public markAsSeenNotifications() {
        this.notificationService.see(this.notificationModels).subscribe(() => {
            this.notificationModels = [];
        });
    }
}
