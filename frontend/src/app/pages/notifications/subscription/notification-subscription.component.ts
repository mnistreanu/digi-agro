import {Component, OnInit} from '@angular/core';
import {NotificationSubscriptionService} from '../../../services/notification/notification-subscription.service';
import {NotificationSubscriptionModel} from './notification-subscription.model';
import {AlertService} from '../../../services/alert.service';

@Component({
    selector: 'app-notification-subscription',
    templateUrl: './notification-subscription.component.html',
    styleUrls: ['./notification-subscription.component.scss']
})
export class NotificationSubscriptionComponent implements OnInit {

    public models: NotificationSubscriptionModel[] = [];

    constructor(private alertService: AlertService,
                private notificationSubscriptionService: NotificationSubscriptionService) {
    }

    ngOnInit() {
        this.setupModels();
    }

    private setupModels() {
        this.notificationSubscriptionService.find().subscribe(items => {
            this.models = items;
            this.models.forEach((model) => {
                model.typeModel.image = '../assets/img/notifications/' + model.typeModel.key + '.png';
            });
        });
    }

    changeSubscription(event, model) {
        model.subscribed = event.target.checked;
        this.notificationSubscriptionService.changeSubscription(model.typeModel.id, model.subscribed).subscribe(() => {
            this.alertService.saved();
        });
    }

}
