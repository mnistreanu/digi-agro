import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {PinnedRowRendererComponent} from '../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {ExpenseSeasonListModel} from './expense-season-list.model';
import {FieldMapper} from '../../../common/field.mapper';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {CropSeasonListModel} from "../../manage-crops/crop-season/list/crop-season-list.model";

@Component({
    selector: 'app-expense-season-list',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './expense-season-list.component.html',
    styleUrls: ['./expense-season-list.component.scss']
})
export class ExpenseSeasonListComponent implements OnInit {
    options: GridOptions;
    context;

    constructor(private cropSeasonService: CropSeasonService,
                private langService: LangService) {

    }

    ngOnInit() {
        this.setupGrid();
        this.setupTreeData();
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = { pinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};
    }

    private setupHeaders() {

        this.options.defaultColDef = {
            pinnedRowCellRenderer: 'pinnedRowRenderer',
            pinnedRowCellRendererParams: { style: { fontWeight: 'bold' } } // color: 'blue'
        };

        const headers: ColDef[] = [
            {
                headerName: 'crop.season',
                field: 'season',
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
                minWidth: 50
            },
            {
                headerName: 'month.feb',
                field: 'feb',
                width: 50,
                minWidth: 50,
                suppressFilter: true
            },
            {
                headerName: 'month.mar',
                field: 'mar',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.apr',
                field: 'apr',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.may',
                field: 'may',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.jun',
                field: 'jun',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.jul',
                field: 'jul',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.aug',
                field: 'aug',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.sep',
                field: 'sep',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.oct',
                field: 'oct',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.nov',
                field: 'nov',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'month.dec',
                field: 'dec',
                width: 50,
                minWidth: 50
            },
            {
                headerName: 'general.total',
                field: 'total',
                width: 50,
                minWidth: 50
            }
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    private setupTreeData() {
        const treeModels: ExpenseSeasonListModel[] = [];
        this.cropSeasonService.find().subscribe(seasonModels => {
            this.cropSeasonService.adjustListModels(seasonModels);
            seasonModels.forEach((model: CropSeasonListModel) => {
                let a = new ExpenseSeasonListModel();
                a.season = model.harvestYearCropVariety;
                treeModels.push(a);
            });
            debugger;
            this.adjustModels(treeModels);
            this.options.api.setRowData(treeModels);
            this.setupSummaryRow(treeModels);
        });
    }

    private adjustModels(models: ExpenseSeasonListModel[]) {
        const mapper = new FieldMapper(this.langService.getLanguage());
        const season = mapper.get('season');
        const expenseType = mapper.get('expenseType');
        models.forEach((model: ExpenseSeasonListModel) => {
            model.expenseType = model[season];
            model.children.forEach(child => {
                child.jan = Math.round(Math.random() * 100);
                child.feb = Math.round(Math.random() * 100);
                child.mar = Math.round(Math.random() * 100);
                child.apr = Math.round(Math.random() * 100);
                child.may = Math.round(Math.random() * 100);
                child.jun = Math.round(Math.random() * 100);
                child.jul = Math.round(Math.random() * 100);
                child.aug = Math.round(Math.random() * 100);
                child.sep = Math.round(Math.random() * 100);
                child.oct = Math.round(Math.random() * 100);
                child.nov = Math.round(Math.random() * 100);
                child.dec = Math.round(Math.random() * 100);
                child.total = child.jan + child.feb + child.mar + child.apr + child.may + child.jun +
                              child.jul + child.aug + child.sep + child.oct + child.nov + child.dec;

                this.aggregate(model, child);
                child.expenseType = child[expenseType];
            });
        });
    }

    private aggregate(source: ExpenseSeasonListModel, item: ExpenseSeasonListModel) {

        const sumFields = ['jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec', 'total'];

        sumFields.forEach(field => {
            source[field] = source[field] || 0;
            source[field] += item[field] || 0;
        });
    }

    private setupSummaryRow(rows) {
        const summaryRow = new ExpenseSeasonListModel();
        summaryRow.expenseType = 'TOTAL';
        rows.forEach(row => {
            row.children.forEach(child => {
                this.aggregate(summaryRow, child);
            });
        });
        this.options.api.setPinnedBottomRowData([summaryRow]);
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
