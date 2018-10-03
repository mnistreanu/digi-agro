import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, ColGroupDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {ImageRendererComponent} from '../../../../modules/aggrid/image-renderer/image-renderer.component';
import {SowingExpenseService} from '../../../../services/expenses/sowing-expense.service';
import {SowingExpensesListModel} from './sowing-expenses-list.model';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {DateUtil} from "../../../../common/dateUtil";

@Component({
    selector: 'app-sowing-expenses-list',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './sowing-expenses-list.component.html',
    styleUrls: ['./sowing-expenses-list.component.scss']
})
export class SowingExpensesListComponent implements OnInit {
    readOnly;

    options: GridOptions;
    context;
    //
    // labelDate: string;
    // labelCrop: string;
    // labelCropVariety: string;
    // labelSeeds: string;
    // labelUnitOfMeasureShort: string;
    // labelUnitOfMeasureLong: string;
    // labelArea: string;
    // labelParcels: string;
    // labelExpenses: string;
    // labelConsumed1Ha: string;
    // labelConsumedTotal: string;
    // labelPriceUnit: string;
    // labelCostTotal: string;

    constructor(private langService: LangService,
                private sowingExpenseService: SowingExpenseService) {
    }

    ngOnInit() {
        this.setupGrid();
    }

    // private setupLabels() {
    //     this.langService.get('crop.planting-date').subscribe(msg => this.labelDate = msg);
    //     this.langService.get('crop.name').subscribe(msg => this.labelCrop = msg);
    //     this.langService.get('crop.variety').subscribe(msg => this.labelCropVariety = msg);
    //     this.langService.get('crop.seeds').subscribe(msg => this.labelSeeds = msg);
    //     this.langService.get('crop.unit-of-measure-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
    //     this.langService.get('crop.unit-of-measure-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
    //     this.langService.get('parcel.area').subscribe(msg => this.labelArea = msg);
    //     this.langService.get('parcel.parcels').subscribe(msg => this.labelParcels = msg);
    //     this.langService.get('expenses.sowing').subscribe(msg => this.labelExpenses = msg);
    //     this.langService.get('crop.labelConsumed1Ha').subscribe(msg => this.labelConsumed1Ha = msg);
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

        let headers: (ColDef | ColGroupDef)[] = [];

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
                headerName: 'expenses.date',
                field: 'expenseDate',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'crop.seeds',
                field: 'cropAndVariety',
                width: 180,
                minWidth: 180
            },
            // {
            //     headerName: 'crop.variety',
            //     field: 'variety',
            //     width: 180,
            //     minWidth: 180
            // },
            {
                headerName: 'crop.unit-of-measure-short',
                field: 'unitOfMeasure',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                headerTooltip: 'crop.unit-of-measure-long',
            },
            {
                headerName: 'parcel.area',
                field: 'area',
                width: 50,
                minWidth: 50,
                suppressFilter: true,
            },
            // {
            //     headerName: 'parcel.parcels',
            //     field: 'parcels',
            //     width: 200,
            //     minWidth: 200,
            //     suppressFilter: true,
            // },
            {
                headerName: 'expenses.norm-at',
                children: [
                    {
                        headerName: 'parcel.one-ha',
                        field: 'normSow1Ha',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'parcel.total-ha',
                        field: 'normSowTotal',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    }
                ]
            },
            {
                headerName: 'expenses.sowing',
                children: [
                    {
                        headerName: 'parcel.one-ha',
                        field: 'actualSown1Ha',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'parcel.total-ha',
                        field: 'actualSownTotal',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'expenses.unit-cost',
                        field: 'unitPrice',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'expenses.total-cost',
                        field: 'totalAmount',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    }
                ]
            },
            {
                headerName: 'Registered',
                children: [
                    {
                        headerName: 'By',
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
                ]
            },
        ]);

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);

                // if (header.children) {
                //     header.children.forEach(childHeader => {
                //         if (childHeader.headerName) {
                //             this.langService.get(childHeader.headerName).subscribe(m => childHeader.headerName = m);
                //         }
                //     });
                // }
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }


    public setupRows() {
        this.sowingExpenseService.find().subscribe(models => {
            models.forEach(model => {
                model.cropAndVariety = model.crop + ' ' + model.variety;
                model.unitOfMeasure = this.langService.instant('unit-of-measure.' + model.unitOfMeasure);
            });

            debugger;
            this.options.api.setRowData(models);
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
