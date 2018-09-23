import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {MachineTelemetryModel} from './machine-telemetry.model';
import {NumericUtil} from '../../../common/numericUtil';
import {DeleteRendererComponent} from '../../../modules/aggrid/delete-renderer/delete-renderer.component';
import {DateUtil} from '../../../common/dateUtil';
import {MachineService} from '../../../services/machine.service';
import {LangService} from '../../../services/lang.service';
import {MachineTelemetryService} from '../../../services/machine-telemetry.service';
import {MachineModel} from '../../manage-machines/machine/machine.model';
import {MessageService} from '../../../services/message.service';

@Component({
    selector: 'app-machine-telemetry',
    templateUrl: './machine-telemetry.component.html',
    styleUrls: ['./machine-telemetry.component.scss']
})
export class MachineTelemetryComponent implements OnInit {

    @Output() dataChanged: EventEmitter<MachineTelemetryModel[]> = new EventEmitter<MachineTelemetryModel[]>();

    options: GridOptions;
    context;

    selectedMachine: MachineModel;
    machines: MachineModel[];

    models: MachineTelemetryModel[] = [];

    constructor(private machineTelemetryService: MachineTelemetryService,
                private machineService: MachineService,
                private langService: LangService,
                private messageService: MessageService) {
    }


    ngOnInit(): void {
        this.setupMachines()
            .then(() => this.setupGrid());
    }

    private setupMachines(): Promise<void> {
        return new Promise((resolve) => {
            this.machineService.findAll().subscribe(data => {
                this.machines = data;

                if (data && data.length > 0) {
                    this.selectedMachine = this.machines[0];
                }

                resolve();
            });
        });
    }

    public onMachineChange() {
        this.setupRows();
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

        const headers: ColDef[] = [
            {
                headerName: 'geo.latitude',
                field: 'latitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onCoordinateChange(params)
            },
            {
                headerName: 'geo.longitude',
                field: 'longitude',
                width: 100,
                minWidth: 100,
                editable: true,
                valueSetter: (params) => this.coordinateValueSetter(params),
                onCellValueChanged: (params) => this.onCoordinateChange(params)
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

    private onCoordinateChange(params) {

        const model = params.data;
        const field = params.colDef.field;
        const value = params.newValue;

        this.machineTelemetryService.updateCoordinate(model.id, field, value).subscribe(() => {
            this.messageService.saved();
            this.dataChanged.emit(this.models);
        });
    }

    public setupRows() {
        if (!this.selectedMachine) {
            return;
        }
        this.machineTelemetryService.find(this.selectedMachine.id).subscribe(models => {
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
        const item = new MachineTelemetryModel();

        item.machineId = this.selectedMachine.id;
        item.createdAt = new Date();
        item.latitude = 0;
        item.longitude = 0;

        this.options.api.updateRowData({add: [item]});

        this.machineTelemetryService.save(item).subscribe((savedModel) => {
            item.id = savedModel.id;

            this.models.push(item);
            this.dataChanged.emit(this.models);
            this.messageService.saved();
        });
    }

    public onDelete(node) {
        const model = node.data;
        this.options.api.updateRowData({remove: [model]});

        this.machineTelemetryService.remove(model).subscribe(() => {
            this.models.splice(this.models.indexOf(model), 1);
            this.dataChanged.emit(this.models);
            this.messageService.removed();
        });
    }

}
