import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MapEventModel} from './map-event.model';
import {ColDef, GridOptions} from 'ag-grid';
import {MapEventService} from '../../../services/map-event.service';
import {ToastrService} from 'ngx-toastr';
import {DeleteRendererComponent} from '../../../modules/aggrid/delete-renderer/delete-renderer.component';
import {DateUtil} from '../../../common/dateUtil';
import {Messages} from '../../../common/messages';
import {NumericUtil} from '../../../common/numericUtil';
import {LangService} from '../../../services/lang.service';
import {AlertService} from '../../../services/alert.service';

@Component({
    selector: 'app-map-events',
    templateUrl: './map-events.component.html',
    styleUrls: ['./map-events.component.scss']
})
export class MapEventsComponent implements OnInit {


    @Output() dataChanged: EventEmitter<MapEventModel[]> = new EventEmitter<MapEventModel[]>();

    options: GridOptions;
    context;

    models: MapEventModel[] = [];

    constructor(private mapEventService: MapEventService,
                private langService: LangService,
                private alertService: AlertService) {
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
                headerName: 'event.message',
                field: 'message',
                width: 200,
                minWidth: 200,
                editable: true,
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'geo.latitude',
                field: 'latitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'geo.longitude',
                field: 'longitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'info.creation-time',
                field: 'createdAt',
                width: 120,
                minWidth: 170,
                maxWidth: 170,
                valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
            },
            {
                field: 'delete',
                headerName: '',
                width: 24,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: DeleteRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private coordinateValueSetter(params) {
        const newValue = params.newValue;
        if (newValue == params.oldValue) {
            return false;
        }

        if (!NumericUtil.isNumeric(newValue)) {
            return false;
        }

        const field = params.colDef.field;
        params.data[field] = newValue;

        return true;
    }

    private onDataChange(params) {

        const model = params.data;
        const field = params.colDef.field;
        const value = params.newValue;

        this.mapEventService.update(model.id, field, value).subscribe(() => {
            this.alertService.saved();
            this.dataChanged.emit(this.models);
        });
    }

    public setupRows() {
        this.mapEventService.find().subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.dataChanged.emit(this.models);
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

    public add() {
        const item = new MapEventModel();

        item.createdAt = new Date();
        item.latitude = 0;
        item.longitude = 0;
        item.message = 'New Event';

        this.options.api.updateRowData({add: [item]});

        this.mapEventService.save(item).subscribe((savedModel) => {
            item.id = savedModel.id;

            this.models.push(item);
            this.dataChanged.emit(this.models);

            this.alertService.saved();
        });
    }

    public onDelete(node) {
        const model = node.data;
        this.options.api.updateRowData({remove: [model]});

        this.mapEventService.remove(model).subscribe(() => {
            this.models.splice(this.models.indexOf(model), 1);
            this.dataChanged.emit(this.models);
            this.alertService.removed();
        });
    }


}
