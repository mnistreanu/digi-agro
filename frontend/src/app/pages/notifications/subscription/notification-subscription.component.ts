import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {NotificationService} from "../../../services/notification.service";
import {ColDef, GridOptions} from "ag-grid";
import {ListItem} from "../../../interfaces/list-item.interface";

@Component({
    selector: 'az-notification-subscription',
    templateUrl: './notification-subscription.component.html',
    styleUrls: ['./notification-subscription.component.scss']
})
export class NotificationSubscriptionComponent implements OnInit {

    context;
    public listItems: ListItem[];
    public notificationTypes:Array<Object> = [];

    constructor(private router: Router, private notificationService: NotificationService) {
    }

    ngOnInit() {
        this.findNotificationTypes();
    }

    private findNotificationTypes() {
        this.notificationService.findTypes().subscribe(payloadModel => {
            this.listItems = payloadModel.payload;

            this.listItems.forEach((model) => {
                let notificationType: any = {};
                notificationType.id = model.id;
                notificationType.name = model.name;
                notificationType.image = '../assets/img/notifications/'+model.name+ '.png';
                this.notificationTypes.push(notificationType);
            });
        })
    }


}
