import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from "@angular/core";
import {ColDef, GridOptions} from "ag-grid";
import {TelemetryModel} from "../telemetry/telemetry.model";
import {TelemetryService} from "../../../services/telemetry.service";
import {ToastrService} from "ngx-toastr";
import {NumericUtil} from "../../../common/numericUtil";
import {DeleteRendererComponent} from "../../../modules/aggrid/delete-renderer/delete-renderer.component";
import {Messages} from "../../../common/messages";
import {DateUtil} from "../../../common/dateUtil";

@Component({
    selector: 'az-coordinates',
    templateUrl: './coordinates.component.html',
    styleUrls: ['./coordinates.component.scss']
})
export class CoordinatesComponent implements OnInit, OnChanges {

    options: GridOptions;
    context;

    @Input() username: string;
    @Input() machineIdentifier: string;

    @Output() coordinateChanged: EventEmitter<TelemetryModel[]> = new EventEmitter<TelemetryModel[]>();

    models: TelemetryModel[] = [];

    constructor(private telemetryService: TelemetryService,
                private toastr: ToastrService) {
    }


    ngOnInit(): void {
        this.setupGrid();
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes.machineIdentifier) {
            this.setupRows();
        }
    }

    public setupGrid() {
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
                headerName: 'Machine Identifier',
                field: 'machineIdentifier',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Latitude',
                field: 'latitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onCoordinateChange(params)
            },
            {
                headerName: 'Longitude',
                field: 'longitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onCoordinateChange(params)
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

    private onCoordinateChange(params) {

        let model = params.data;
        let field = params.colDef.field;
        let value = params.newValue;

        this.telemetryService.updateCoordinate(model.id, field, value).subscribe(() => {
            this.toastr.success(Messages.SAVED);
            this.coordinateChanged.emit(this.models);
        });
    }

    public setupRows() {
        console.log('coord', this.machineIdentifier);
        this.telemetryService.findByMachineIdentifierAndUsername(this.machineIdentifier, this.username).subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.coordinateChanged.emit(this.models);
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
        let item = new TelemetryModel();

        item.machineIdentifier = this.machineIdentifier;
        item.username = this.username;
        item.createdAt = new Date();
        item.latitude = 0;
        item.longitude = 0;

        this.options.api.updateRowData({add: [item]});

        this.telemetryService.save(item).subscribe((savedModel) => {
            item.id = savedModel.id;

            this.models.push(item);
            this.coordinateChanged.emit(this.models);

            this.toastr.success(Messages.ADDED);
        });
    }

    public onDelete(node) {
        let model = node.data;
        this.options.api.updateRowData({remove: [model]});

        this.telemetryService.remove(model).subscribe(() => {
            this.models.splice(this.models.indexOf(model), 1);
            this.coordinateChanged.emit(this.models);
            this.toastr.success(Messages.DELETED);
        });
    }


    setMachineIdentifier(machineIdentifier: string) {
        this.machineIdentifier = machineIdentifier;
    }
}
