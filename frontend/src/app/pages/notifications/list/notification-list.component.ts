import {Component, OnInit} from '@angular/core';
import {NotificationService} from '../../../services/notification/notification.service';
import {Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {NotificationModel} from '../notification.model';
import {LangService} from '../../../services/lang.service';
import {DateUtil} from '../../../common/dateUtil';
import {ImageRendererComponent} from '../../../modules/aggrid/image-renderer/image-renderer.component';

@Component({
    selector: 'app-notification-list',
    templateUrl: './notification-list.component.html',
    styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private langService: LangService,
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

        const headers: ColDef[] = [
            {
                headerName: '',
                field: 'image',
                cellRendererFramework: ImageRendererComponent,
                cellRendererParams: {
                    iconField: 'image'
                },
                cellStyle: () => {
                    return {padding: 0};
                },
                width: 50,
                minWidth: 50,
                maxWidth: 50,
                suppressResize: true,
                suppressMenu: true
            },
            {
                headerName: 'Type',
                field: 'type',
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
                headerName: 'Days to',
                field: 'durationDays',
                width: 200,
                minWidth: 100
            },
            {
                headerName: 'Hours to',
                field: 'durationHours',
                width: 200,
                minWidth: 100
            },
            {
                headerName: 'Seen At',
                field: 'seenAt',
                filter: 'agDateColumnFilter',
                filterParams: {
                    comparator: (d1, d2) => DateUtil.compareWithoutTime(d1, d2)
                },
                valueFormatter: params => DateUtil.formatLocalizedDate(params.value),
                width: 200,
                minWidth: 160
            }
        ];

        return headers;
    }

    private setupRows() {
        this.notificationService.findAll().subscribe(models => {
            this.adjustNotificationTypes(models);
            this.adjustNotificationImages(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustNotificationImages(models: NotificationModel[]) {
        models.forEach((model) => {
            model.image = '../assets/img/notifications/' + model.translationKey + '.png';
        });
    }

    private adjustNotificationTypes(models: NotificationModel[]) {
        models.forEach((model) => {
            this.langService.get('notification.' + model.translationKey).subscribe(msg => model.type = msg);
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            if (this.options && this.options.api) {
                this.options.api.sizeColumnsToFit();
            }
        }, 500);
    }

}
