import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../../../services/notification.service";
import {Router} from "@angular/router";
import {ColDef, GridOptions} from "ag-grid";
import {NotificationModel} from "../notification.model";

@Component({
  selector: 'az-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

    public notificationModels: NotificationModel[];
    options: GridOptions;
    context;

    constructor(private router: Router,
                private notificationService: NotificationService) {
    }

    ngOnInit() {
        this.setupGrid();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

        this.setupRows();

    }

    private setupHeaders() {

        let headers: ColDef[] = [
            {
                headerName: '',
//                image: '../assets/img/notifications/'+translationKey+ '.png',
                field: 'translationKey',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Type',
                field: 'translationKey',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'Message',
                field: 'message',
                width: 1000,
                minWidth: 400
            },
            {
                headerName: 'Time to',
                field: 'durationDays',
                width: 200,
                minWidth: 100
            },
            {
                headerName: 'Time to',
                field: 'durationHours',
                width: 200,
                minWidth: 100
            },
            {
                headerName: 'Seen At',
                field: 'seenAt',
                width: 200,
                minWidth: 100
            }
        ];

        return headers;
    }

    private setupRows() {
        this.notificationService.find().subscribe(payloadModel => {
            this.notificationModels = payloadModel.payload;
            this.options.api.setRowData(this.notificationModels);
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {this.options.api.sizeColumnsToFit();}, 500);
    }

    public add() {
        this.router.navigate(['/pages/manage-notifications/notification/-1']);
    }

    public onEdit(node) {
        let model = node.data;
        this.router.navigate(['/pages/manage-notifications/notification/' + model.id]);
    }

}
