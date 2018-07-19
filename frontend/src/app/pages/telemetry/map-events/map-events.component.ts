import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from "@angular/core";
import {MapEventModel} from "./map-event.model";
import {ColDef, GridOptions} from "ag-grid";
import {MapEventService} from "../../../services/map-event.service";
import {ToastrService} from "ngx-toastr";
import {DeleteRendererComponent} from "../../../modules/aggrid/delete-renderer/delete-renderer.component";
import {DateUtil} from "../../../common/dateUtil";
import {Messages} from "../../../common/messages";
import {NumericUtil} from "../../../common/numericUtil";

@Component({
    selector: 'az-map-events',
    templateUrl: './map-events.component.html',
    styleUrls: ['./map-events.component.scss']
})
export class MapEventsComponent implements OnInit, OnChanges {

    @Input() username;
    @Input() machineIdentifier;

    @Output() mapEventChanged: EventEmitter<MapEventModel[]> = new EventEmitter<MapEventModel[]>();

    options: GridOptions;
    context;

    models: MapEventModel[] = [];

    constructor(private mapEventService: MapEventService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupGrid();
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes.machineIdentifier) {
            this.setupRows();
        }
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
                headerName: 'Message',
                field: 'message',
                width: 200,
                minWidth: 200,
                editable: true,
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'Latitude',
                field: 'latitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'Longitude',
                field: 'longitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onDataChange(params)
            },
            {
                headerName: 'Created At',
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

        return headers;
    }

    private coordinateValueSetter(params) {
        let newValue = params.newValue;
        if (newValue == params.oldValue) {
            return false;
        }

        if (!NumericUtil.isNumeric(newValue)) {
            return false;
        }

        let field = params.colDef.field;
        params.data[field] = newValue;

        return true;
    }

    private onDataChange(params) {

        let model = params.data;
        let field = params.colDef.field;
        let value = params.newValue;

        this.mapEventService.update(model.id, field, value).subscribe(() => {
            this.toastr.success(Messages.SAVED);
            this.mapEventChanged.emit(this.models);
        });
    }

    public setupRows() {
        this.mapEventService.findByMachineIdentifierAndUsername(this.machineIdentifier, this.username).subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.mapEventChanged.emit(this.models);
        });
    }


    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            this.options.api.sizeColumnsToFit();
        }, 500);
    }

    public add() {
        let item = new MapEventModel();

        item.machineIdentifier = this.machineIdentifier;
        item.username = this.username;
        item.createdAt = new Date();
        item.latitude = 0;
        item.longitude = 0;
        item.message = 'New Event';

        this.options.api.updateRowData({add: [item]});

        this.mapEventService.save(item).subscribe((savedModel) => {
            item.id = savedModel.id;

            this.models.push(item);
            this.mapEventChanged.emit(this.models);

            this.toastr.success(Messages.ADDED);
        });
    }

    public onDelete(node) {
        let model = node.data;
        this.options.api.updateRowData({remove: [model]});

        this.mapEventService.remove(model).subscribe(() => {
            this.models.splice(this.models.indexOf(model), 1);
            this.mapEventChanged.emit(this.models);
            this.toastr.success(Messages.REMOVED);
        });
    }


}
