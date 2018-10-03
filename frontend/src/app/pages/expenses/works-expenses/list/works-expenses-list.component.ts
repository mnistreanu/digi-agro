import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {MachineService} from '../../../../services/machine.service';
import {WorksExpensesListModel} from './works-expenses-list.model';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {WorksExpenseService} from '../../../../services/expenses/works-expense.service';

@Component({
    selector: 'app-works-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './works-expenses-list.component.html',
    styleUrls: ['./works-expenses-list.component.scss']
})
export class WorksExpensesListComponent implements OnInit {
    readOnly;
    options: GridOptions;
    context;

    // labelDate: string;
    // labelAgroWorkType: string;
    // labelCrop: string;
    // labelUnitOfMeasureShort: string;
    // labelUnitOfMeasureLong: string;
    // labelQuantity: string;
    // labelNorm: string;
    // labelDefacto: string;
    // labelPrice1Norm: string;
    // labelSum: string;
    // labelMachineType: string;
    // labelAgriculturalMachinery: string;
    // labelIdentifier: string;
    // labelFirstName: string;
    // labelLastName: string;

    constructor(private machineService: MachineService,
                private worksExpenseService: WorksExpenseService,
                private langService: LangService) {
    }

    ngOnInit() {
        // this.setupLabels();
        this.setupGrid();
    }
    //
    // private setupLabels() {
    //     this.langService.get('agro-work.date').subscribe(msg => this.labelDate = msg);
    //     this.langService.get('agro-work.type').subscribe(msg => this.labelAgroWorkType = msg);
    //     this.langService.get('crop.name').subscribe(msg => this.labelCrop = msg);
    //     this.langService.get('unit-of-measure.unit-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
    //     this.langService.get('unit-of-measure.unit-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
    //     this.langService.get('agro-work.quantity').subscribe(msg => this.labelQuantity = msg);
    //     this.langService.get('agro-work.norm').subscribe(msg => this.labelNorm = msg);
    //     this.langService.get('agro-work.defacto').subscribe(msg => this.labelDefacto = msg);
    //     this.langService.get('agro-work.price-1-norm').subscribe(msg => this.labelPrice1Norm = msg);
    //     this.langService.get('agro-work.sum').subscribe(msg => this.labelSum = msg);
    //     this.langService.get('machine.type').subscribe(msg => this.labelMachineType = msg);
    //     this.langService.get('machine.agricultural-machinery').subscribe(msg => this.labelAgriculturalMachinery = msg);
    //     this.langService.get('machine.identifier').subscribe(msg => this.labelIdentifier = msg);
    //     this.langService.get('employee.first-name').subscribe(msg => this.labelFirstName = msg);
    //     this.langService.get('employee.last-name').subscribe(msg => this.labelLastName = msg);
    // }


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

        let headers: ColDef[] = [];

        if (!this.readOnly) {
            headers.push({
                field: 'edit',
                width: 24,
                minWidth: 24,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            });
        }

        headers = headers.concat([
            {
                headerName: 'agro-work.date',
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
            },
            {
                headerName: 'agro-work.type',
                field: 'workType',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'crop.name',
                field: 'crop',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'unit-of-measure.unit-short',
                headerTooltip: 'unit-of-measure.unit-long',
                field: 'unitOfMeasure',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.quantity',
                headerTooltip: 'expenses.quantity',
                field: 'quantity',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.norm',
                headerTooltip: 'expenses.norm',
                field: 'quantityNorm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.defacto',
                headerTooltip: 'expenses.defacto',
                field: 'quantityDefacto',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.price-1-norm',
                headerTooltip: 'expenses.price-1-norm',
                field: 'price1Norm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.sum',
                field: 'sum',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'machine.agricultural-machinery',
                field: 'brandModel',
                width: 180,
                minWidth: 180
            },
            // {
            //     headerName: this.labelIdentifier,
            //     field: 'identifier',
            //     width: 60,
            //     minWidth: 60,
            //     suppressFilter: true,
            // },
            {
                headerName: 'employee.first-last-name' ,
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
        ]);


        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);

                if (header.children) {
                    header.children.forEach(childHeader => {
                        if (childHeader.headerName) {
                            this.langService.get(childHeader.headerName).subscribe(m => childHeader.headerName = m);
                        }
                    });
                }
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }


    public setupRows() {
        let i = 0;
        this.machineService.findAll().subscribe(modelsArray => {
            const rows = modelsArray.map(data => {
                const model = new WorksExpensesListModel();
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
