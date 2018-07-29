import {Component, ViewEncapsulation} from '@angular/core';

import {MessagesService} from '../../../services/messages.service';
import {AgroTaskService} from '../../../services/agro-task.service';
import {AgroTaskModel} from "../../../pages/agro-task-calendar/agro-task.model";
import {NotificationModel} from "../../../pages/notification.model";
import {NotificationService} from "../../../services/notification.service";

@Component({
    selector: 'az-messages',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./messages.component.scss'],
    templateUrl: './messages.component.html',
    providers: [MessagesService, NotificationService, AgroTaskService]
})

export class MessagesComponent{     
    public messages:Array<Object> = [];

    public notifications:Array<Object> = [];
    public notificationModels: NotificationModel[];

    public tasks:Array<Object> = [];
    public agrotaskModels: AgroTaskModel[];

    constructor (private messagesService:MessagesService, private agroTaskService:AgroTaskService,
                 private notificationService: NotificationService){
        this.messages = messagesService.getMessages();
        this.findNotifications();
        this.findAgroTasks();
    }

    private findAgroTasks() {
        // task.text: 'Design some buttons',
        // task.value: '20%',
        // task.class: 'info'

        this.agroTaskService.find().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.agrotaskModels = payloadModel.payload;
            // this.tasks = new Array(0);

            this.agrotaskModels.forEach((model) => {
                let agroTask: any = {};
                agroTask.id = model.id;
                agroTask.workTypeId = model.workTypeId;
                agroTask.text = model.title;
                agroTask.value = '20%';
                agroTask.class = this.getTaskClass(agroTask.workTypeId);
                this.tasks.push(agroTask); 
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
            // this.notifications = new Array(0);

            this.notificationModels.forEach((model) => {
                let notification: any = {};
                notification.id = model.id;
                notification.typeId = model.typeId;
                notification.name = 'METEO'; //TODO  de extras denumirea tabelul notification_type
                notification.text = model.message;
                notification.time = 'peste ' + model.typeId + ' zile'; //TODO de facut corect
                notification.image = '../assets/img/notifications/'+model.translationKey+ '.png';
                this.notifications.push(notification);
            });

        });
    }
}