import {Component, OnInit} from "@angular/core";
import {ToastrService} from "ngx-toastr";
import {LangService} from "../../../services/lang.service";
import {Messages} from "../../../common/messages";
import {NotificationSubscriptionService} from "../../../services/notification/notification-subscription.service";
import {NotificationSubscriptionModel} from "./notification-subscription.model";

@Component({
    selector: 'az-notification-subscription',
    templateUrl: './notification-subscription.component.html',
    styleUrls: ['./notification-subscription.component.scss']
})
export class NotificationSubscriptionComponent implements OnInit {

    public models: NotificationSubscriptionModel[] = [];

    private labelSaved: string;

    constructor(private toastrService: ToastrService,
                private langService: LangService,
                private notificationSubscriptionService: NotificationSubscriptionService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupModels();
    }

    private setupLabels() {
        this.langService.get(Messages.SAVED).subscribe(msg => this.labelSaved = msg);
    }

    private setupModels() {
        this.notificationSubscriptionService.find().subscribe(items => {
            this.models = items;
            this.models.forEach((model) => {
                model.typeModel.image = '../assets/img/notifications/' + model.typeModel.key + '.png';
            });
        })
    }

    changeSubscription(event, model) {
        model.subscribed = event.target.checked;
        this.notificationSubscriptionService.changeSubscription(model.typeModel.id, model.subscribed).subscribe(() => {
            this.toastrService.success(this.labelSaved);
        });
    }

}
