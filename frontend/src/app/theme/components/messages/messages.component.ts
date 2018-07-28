import {Component, ViewEncapsulation} from '@angular/core';

import {MessagesService} from '../../../services/messages.service';
import {AgroTaskService} from '../../../services/agro-task.service';
import {AgroTaskModel} from "../../../pages/agro-task-calendar/agro-task.model";

@Component({
    selector: 'az-messages',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./messages.component.scss'],
    templateUrl: './messages.component.html',
    providers: [MessagesService]
})

export class MessagesComponent{     
    public messages:Array<Object>;
    public notifications:Array<Object>;
    public tasks:Array<Object>;
    public agrotaskModels: AgroTaskModel[];

    constructor (private messagesService:MessagesService, private agroTaskService:AgroTaskService){
        this.messages = messagesService.getMessages();
        this.notifications = messagesService.getNotifications();
        this.tasks = this.findAgroTasks();
    }

    private findAgroTasks() {
        // task.text: 'Design some buttons',
        // task.value: '20%',
        // task.class: 'info'

        this.agroTaskService.find().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.agrotaskModels = payloadModel.payload;
            this.tasks = new Array(0);

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
}