import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {MachineService} from '../../../../services/machine.service';
import {WorksExpensesModel} from './works-expenses-list.model';

@Component({
    selector: 'app-works-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './works-expenses-list.component.html',
    styleUrls: ['./works-expenses-list.component.scss']
})
export class WorksExpensesComponent implements OnInit {
    options: GridOptions;
    context;

    labelDate: string;
    labelAgroWorkType: string;
    labelCrop: string;
    labelUnitOfMeasureShort: string;
    labelUnitOfMeasureLong: string;
    labelQuantity: string;
    labelNorm: string;
    labelDefacto: string;
    labelPrice1Norm: string;
    labelSum: string;
    labelMachineType: string;
    labelAgriculturalMachinery: string;
    labelIdentifier: string;
    labelFirstName: string;
    labelLastName: string;

    constructor(private machineService: MachineService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('agro-work.date').subscribe(msg => this.labelDate = msg);
        this.langService.get('agro-work.type').subscribe(msg => this.labelAgroWorkType = msg);
        this.langService.get('crop.name').subscribe(msg => this.labelCrop = msg);
        this.langService.get('unit-of-measure.unit-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
        this.langService.get('unit-of-measure.unit-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
        this.langService.get('agro-work.quantity').subscribe(msg => this.labelQuantity = msg);
        this.langService.get('agro-work.norm').subscribe(msg => this.labelNorm = msg);
        this.langService.get('agro-work.defacto').subscribe(msg => this.labelDefacto = msg);
        this.langService.get('agro-work.price-1-norm').subscribe(msg => this.labelPrice1Norm = msg);
        this.langService.get('agro-work.sum').subscribe(msg => this.labelSum = msg);
        this.langService.get('machine.type').subscribe(msg => this.labelMachineType = msg);
        this.langService.get('machine.agricultural-machinery').subscribe(msg => this.labelAgriculturalMachinery = msg);
        this.langService.get('machine.identifier').subscribe(msg => this.labelIdentifier = msg);
        this.langService.get('employee.first-name').subscribe(msg => this.labelFirstName = msg);
        this.langService.get('employee.last-name').subscribe(msg => this.labelLastName = msg);
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
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
                headerName: this.labelAgroWorkType,
                field: 'workType',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: this.labelCrop,
                field: 'crop',
                width: 180,
                minWidth: 180
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
                headerName: this.labelQuantity,
                headerTooltip: this.labelQuantity,
                field: 'quantity',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelNorm,
                headerTooltip: this.labelNorm,
                field: 'quantityNorm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelDefacto,
                headerTooltip: this.labelDefacto,
                field: 'quantityDefacto',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelPrice1Norm,
                headerTooltip: this.labelPrice1Norm,
                field: 'price1Norm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelSum,
                field: 'sum',
                width: 60,
                minWidth: 60,
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
                headerName: this.labelLastName   + ' ' +this.labelFirstName ,
                field: 'employee',
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
                const model = new WorksExpensesModel();
                model.date = new Date().toLocaleDateString();
                model.workType = 'Cultivat';
                model.crop = 'Porumb';
                model.unitOfMeasure = 'Ha';
                model.quantity = Math.round(Math.random() * 100);
                model.quantityNorm = Math.round(Math.random() * 100);
                model.quantityDefacto = Math.round(Math.random() * 100);
                model.price1Norm = Math.round(Math.random() * 100);
                model.sum = Math.round(Math.random() * 100);
                model.brandModel = modelsArray[i].type + ' ' + modelsArray[i].brand + ' ' + modelsArray[i].model;
                model.identifier = modelsArray[i].identifier;
                model.employee = 'Roată Ion';
                i++;
                return model;
            });
            this.options.api.setRowData(rows);
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
