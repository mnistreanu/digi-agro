import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {ColDef, GridOptions} from "ag-grid";
import {MachineTelemetryModel} from "./machine-telemetry.model";
import {ToastrService} from "ngx-toastr";
import {NumericUtil} from "../../../common/numericUtil";
import {DeleteRendererComponent} from "../../../modules/aggrid/delete-renderer/delete-renderer.component";
import {Messages} from "../../../common/messages";
import {DateUtil} from "../../../common/dateUtil";
import {MachineService} from "../../../services/machine.service";
import {LangService} from "../../../services/lang.service";
import {MachineTelemetryService} from "../../../services/machine-telemetry.service";

@Component({
    selector: 'machine-telemetry',
    templateUrl: './machine-telemetry.component.html',
    styleUrls: ['./machine-telemetry.component.scss']
})
export class MachineTelemetryComponent implements OnInit {

    @Output() coordinateChanged: EventEmitter<MachineTelemetryModel[]> = new EventEmitter<MachineTelemetryModel[]>();

    options: GridOptions;
    context;

    machineIdentifier: string;
    availableMachineIdentifiers: string[];

    models: MachineTelemetryModel[] = [];

    labelAdded: string;
    labelSaved: string;
    labelRemoved: string;

    constructor(private machineTelemetryService: MachineTelemetryService,
                private machineService: MachineService,
                private langService: LangService,
                private toastr: ToastrService) {
    }


    ngOnInit(): void {
        this.setupLabels();
        this.setupMachineIdentifiers();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get(Messages.ADDED).subscribe(msg => this.labelAdded = msg);
        this.langService.get(Messages.SAVED).subscribe(msg => this.labelSaved = msg);
        this.langService.get(Messages.REMOVED).subscribe(msg => this.labelRemoved = msg);
    }

    private setupMachineIdentifiers() {
        this.machineService.fetchIdentifiers().subscribe(data => {
            this.availableMachineIdentifiers = data;

            if (data && data.length > 0) {
                return;
            }

            this.machineIdentifier = this.availableMachineIdentifiers[0];
        })
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

        this.machineTelemetryService.updateCoordinate(model.id, field, value).subscribe(() => {
            this.toastr.success(this.labelSaved);
            this.coordinateChanged.emit(this.models);
        });
    }

    public setupRows() {
        this.machineTelemetryService.find(this.machineIdentifier).subscribe(models => {
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
        let item = new MachineTelemetryModel();

        item.machineIdentifier = this.machineIdentifier;
        item.createdAt = new Date();
        item.latitude = 0;
        item.longitude = 0;

        this.options.api.updateRowData({add: [item]});

        this.machineTelemetryService.save(item).subscribe((savedModel) => {
            item.id = savedModel.id;

            this.models.push(item);
            this.coordinateChanged.emit(this.models);
            this.toastr.success(this.labelAdded);
        });
    }

    public onDelete(node) {
        let model = node.data;
        this.options.api.updateRowData({remove: [model]});

        this.machineTelemetryService.remove(model).subscribe(() => {
            this.models.splice(this.models.indexOf(model), 1);
            this.coordinateChanged.emit(this.models);
            this.toastr.success(this.labelRemoved);
        });
    }

}
