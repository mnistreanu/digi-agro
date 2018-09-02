import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {MachineService} from '../../../services/machine.service';
import {FuelExpensesModel} from './fuel-expenses.model';
import { CustomPinnedRowRenderer } from '../../../modules/aggrid/custom-pinned-row-renderer/custom-pinned-row-renderer.component';

@Component({
    selector: 'app-fuel-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './fuel-expenses.component.html',
    styleUrls: ['./fuel-expenses.component.scss']
})
export class FuelExpensesComponent implements OnInit {
    options: GridOptions;
    context;

    labelMachineType: string;
    labelAgriculturalMachinery: string;
    labelIdentifier: string;
    labelDate: string;
    labelFirstName: string;
    labelLastName: string;
    labelUnitOfMeasureShort: string;
    labelUnitOfMeasureLong: string;
    labelDiesel: string;
    labelOil: string;
    labelSolidol: string;
    labelNegrol: string;

    constructor(private machineService: MachineService, private langService: LangService) {

    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('machine.type').subscribe(msg => this.labelMachineType = msg);
        this.langService.get('machine.agricultural-machinery').subscribe(msg => this.labelAgriculturalMachinery = msg);
        this.langService.get('machine.identifier').subscribe(msg => this.labelIdentifier = msg);
        this.langService.get('machine.repairing-date').subscribe(msg => this.labelDate = msg);
        this.langService.get('employee.first-name').subscribe(msg => this.labelFirstName = msg);
        this.langService.get('employee.last-name').subscribe(msg => this.labelLastName = msg);
        this.langService.get('unit-of-measure.unit-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
        this.langService.get('unit-of-measure.unit-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
        this.langService.get('fuel.diesel').subscribe(msg => this.labelDiesel = msg);
        this.langService.get('fuel.oil').subscribe(msg => this.labelOil = msg);
        this.langService.get('fuel.solidol').subscribe(msg => this.labelSolidol = msg);
        this.langService.get('fuel.negrol').subscribe(msg => this.labelNegrol = msg);
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = { customPinnedRowRenderer: CustomPinnedRowRenderer };

        this.context = {componentParent: this};

        this.setupRows();
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: this.labelDate,
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
            },
            {
                headerName: this.labelAgriculturalMachinery,
                field: 'brandModel',
                width: 180,
                minWidth: 180
            },
            {
                headerName: this.labelIdentifier,
                field: 'identifier',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelLastName  + ' ' + this.labelFirstName,
                field: 'employee',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: this.labelUnitOfMeasureShort,
                headerTooltip: this.labelUnitOfMeasureLong,
                field: 'unitOfMeasure',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelDiesel,
                field: 'diesel',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                // allow gui to set aggregations for this column
                enableValue: true,
                // restrict aggregations to sum
                allowedAggFuncs: ['sum'],
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'blue' } }
            },
            {
                headerName: this.labelOil,
                field: 'oil',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: this.labelSolidol,
                field: 'solidol',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: this.labelNegrol,
                field: 'negrol',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'Registered By',
                field: '',
                width: 100,
                minWidth: 100,
            },
            {
                headerName: 'At',
                field: '',
                width: 60,
                minWidth: 60,
            },

        ];


        return headers;
    }


    public setupRows() {
        let i = 0;
        this.machineService.findAll().subscribe(modelsArray => {
            const rows = modelsArray.map(data => {
                const model = new FuelExpensesModel();
                model.date = new Date().toLocaleDateString();
                model.type = modelsArray[i].type;
                model.brandModel = modelsArray[i].type + ' ' + modelsArray[i].brand + ' ' + modelsArray[i].model;
                model.identifier = modelsArray[i].identifier;
                model.employee = 'Roată Ion';
                model.unitOfMeasure = 'L';
                model.diesel = 120;
                model.oil = 9;
                model.solidol = 1;
                model.negrol = 1;
                i++;
                return model;
            });
            this.options.api.setRowData(rows);
            this.setupSummaryRow(rows);
        });
    }

    private setupSummaryRow(rows) {
        const summaryRow = {
            diesel: 0
        };
        rows.forEach(source => {
            summaryRow.diesel += source.diesel || 0;
        });
        this.options.api.setPinnedBottomRowData([summaryRow]);
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
