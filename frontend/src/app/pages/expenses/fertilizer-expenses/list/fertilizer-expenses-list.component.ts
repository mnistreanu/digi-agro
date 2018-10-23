import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, ColGroupDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {MachineService} from '../../../../services/machine.service';
import {PinnedRowRendererComponent} from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {FertilizerExpensesListModel} from './fertilizer-expenses-list.model';
import {DateUtil} from '../../../../common/dateUtil';
import {FertilizerExpenseService} from '../../../../services/expenses/fertilizer-expense.service';

@Component({
    selector: 'app-fertilizers-expenses-list',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './fertilizer-expenses-list.component.html',
    styleUrls: ['./fertilizer-expenses-list.component.scss']
})
export class FertilizerExpensesListComponent implements OnInit {
    options: GridOptions;
    context;

    constructor(private fertilizerExpenseService: FertilizerExpenseService,
                private langService: LangService) {

    }

    ngOnInit() {
        this.setupGrid();
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = { customPinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};

        this.setupRows();
    }

    private setupHeaders() {

        const headers: (ColDef | ColGroupDef)[] = [
            {
                headerName: 'fertilizer.spray-date',
                headerTooltip: 'fertilizer.spray-date',
                field: 'expenseDate',
                width: 130,
                minWidth: 130,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } },
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'info.name',
                field: 'fertilizerModel.nameRo',
                width: 260,
                minWidth: 260
            },
            {
                headerName: 'fertilizer.origin',
                field: 'fertilizerModel.fertilizerType',
                width: 140,
                minWidth: 140,
                suppressFilter: true,
            },
            {
                headerName: 'fertilizer.phase',
                field: 'phase',
                width: 140,
                minWidth: 140,
                suppressFilter: true,
            },
            {
                headerName: 'fertilizer.result',
                headerTooltip: 'fertilizer.result',
                field: 'result',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'fertilizer.comments',
                headerTooltip: 'fertilizer.comments',
                field: 'comments',
                width: 400,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'info.registered',
                children: [
                    {
                        headerName: 'info.by',
                        field: 'createdBy',
                        width: 100,
                        minWidth: 100,
                    },
                    {
                        headerName: 'info.at',
                        field: 'createdAt',
                        width: 90,
                        minWidth: 90,
                        valueFormatter: (params) => DateUtil.formatDate(params.value)
                    },
                ]
            },
        ];
        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }

            if (header.hasOwnProperty('children')) {
                header['children'].forEach(childHeader => {
                    if (childHeader.headerName) {
                        this.langService.get(childHeader.headerName).subscribe(m => childHeader.headerName = m);
                    }
                });
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }


    public setupRows() {
        this.fertilizerExpenseService.find().subscribe(models => {
            models.forEach(model => {
                model.fertilizerModel.fertilizerType = this.langService.instant('fertilizer-type.' + model.fertilizerModel.fertilizerType);
            });
            this.options.api.setRowData(models);
        });

    }

    private setupSummaryRow(rows) {
        const summaryRow = {
            date: 'TOTAL',
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
