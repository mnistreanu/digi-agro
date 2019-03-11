import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {PinnedRowRendererComponent} from '../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {ExpenseService} from '../../../services/expenses/expense.service';
import {ExpenseSeasonTreeModel} from './expense-season-tree.model';
import {AgNumericColumnType} from '../../../modules/aggrid/column-types/ag-numeric-type';

@Component({
    selector: 'app-expense-season-list',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './expense-season-list.component.html',
    styleUrls: ['./expense-season-list.component.scss']
})
export class ExpenseSeasonListComponent implements OnInit {
    options: GridOptions;
    context;

    constructor(private expenseService: ExpenseService,
                private cropSeasonService: CropSeasonService,
                private langService: LangService) {

    }

    ngOnInit() {
        this.setupGrid();
        this.setupTreeData();
    }


    private setupGrid() {
        this.options = <GridOptions>{};
        this.setupColumnTypes();

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = { pinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};
    }

    private setupColumnTypes() {
        this.options.columnTypes = {
            numericType: AgNumericColumnType.getType()
        };
    }

    private setupHeaders() {

        this.options.defaultColDef = {
            pinnedRowCellRenderer: 'pinnedRowRenderer',
            pinnedRowCellRendererParams: { style: { fontWeight: 'bold' } } // color: 'blue'
        };

        const headers: ColDef[] = [
            {
                headerName: 'crop.season',
                field: 'title',
                width: 200,
                minWidth: 90,
                pinned: 'left',
                cellRenderer: 'agGroupCellRenderer',
                suppressFilter: true
            },
            {
                headerName: 'month.jan',
                field: 'jan',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 1)
            },
            {
                headerName: 'month.feb',
                field: 'feb',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 2)
            },
            {
                headerName: 'month.mar',
                field: 'mar',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 3)
            },
            {
                headerName: 'month.apr',
                field: 'apr',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 4)
            },
            {
                headerName: 'month.may',
                field: 'may',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 5)
            },
            {
                headerName: 'month.jun',
                field: 'jun',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 6)
            },
            {
                headerName: 'month.jul',
                field: 'jul',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 7)
            },
            {
                headerName: 'month.aug',
                field: 'aug',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 8)
            },
            {
                headerName: 'month.sep',
                field: 'sep',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 9)
            },
            {
                headerName: 'month.oct',
                field: 'oct',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 10)
            },
            {
                headerName: 'month.nov',
                field: 'nov',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 11)
            },
            {
                headerName: 'month.dec',
                field: 'dec',
                width: 50,
                minWidth: 50,
                type: 'numericType',
                valueGetter: (params) => this.getMonthValue(params, 12)
            },
            {
                headerName: 'general.total',
                field: 'totalCost',
                width: 50,
                minWidth: 50,
                type: 'numericType'
            }
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    private getMonthValue(params, month) {
        const values = params.data.values;
        return values ? values[month] : null;
    }

    private setupTreeData() {
        this.expenseService.getExpenseSeasonTreeModels().subscribe((models: ExpenseSeasonTreeModel[]) => {
            models.forEach(rootModel => {
                this.cropSeasonService.adjustModel(rootModel.cropSeasonModel);
                rootModel.title = rootModel.cropSeasonModel.harvestYearCropVariety;
                rootModel.children.forEach(child => child.title = child.categoryName);
            });

            this.options.api.setRowData(models);
            this.setupSummaryRow(models);
        });
    }

    private setupSummaryRow(models) {
        const summaryRow = new ExpenseSeasonTreeModel();
        summaryRow.title = 'TOTAL';
        this.aggregate(summaryRow, models);
        this.options.api.setPinnedBottomRowData([summaryRow]);
    }

    private aggregate(model: ExpenseSeasonTreeModel, sourceItems: ExpenseSeasonTreeModel[]) {
        let totalCost = 0;

        sourceItems.forEach(source => {
            if (!source.values) {
                return;
            }

            if (!model.values) {
                model.values = new Map<number, number>();
            }

            Object.keys(source.values).forEach(key => {
                const sourceValue = source.values[key] || 0;
                model.values[key] = model.values[key] || 0;
                model.values[key] = model.values[key] + sourceValue;
                totalCost += sourceValue;
            });
        });

        model.totalCost = totalCost;
    }


    private getNodeChildDetails(item) {
        if (!item.children) {
            return null;
        }

        return {
            group: true,
            expanded: false,
            children: item.children
        };
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
