import {Component, Input, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ExpenseCategoryTotalModel} from '../models/expense-category-total.model';
import {ExpenseService} from '../../../services/expenses/expense.service';
import {LangService} from '../../../services/lang.service';
import {GroupedSelectorComponent} from '../../../modules/aggrid/selector/grouped-selector/grouped-selector.component';
import {AgNumericColumnType} from '../../../modules/aggrid/column-types/ag-numeric-type';
import {ParcelSeasonModel} from '../../manage-farmland/parcel-season.model';
import {NumericUtil} from '../../../common/numericUtil';

@Component({
    selector: 'app-expense-breakdown',
    templateUrl: './expense-breakdown.component.html',
    styleUrls: ['./expense-breakdown.component.scss']
})
export class ExpenseBreakdownComponent implements OnInit {

    @Input() parcelSeasonModel: ParcelSeasonModel;

    options: GridOptions;
    context;

    models: ExpenseCategoryTotalModel[];

    constructor(private expenseService: ExpenseService,
                private langService: LangService) {
    }


    ngOnInit() {
        this.setupGrid();
    }

    private setupGrid() {
        this.options = <GridOptions>{};
        this.setupColumnTypes();

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};
        this.options.frameworkComponents = {
            groupedSelector: GroupedSelectorComponent
        };

        this.setupRows();
    }

    private setupColumnTypes() {
        this.options.columnTypes = {
            numericType: AgNumericColumnType.getType()
        };
    }

    private setupHeaders() {
        const headers: ColDef[] = <ColDef[]>[
            {
                headerName: 'Expense',
                field: 'categoryName',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Total Cost',
                field: 'cost',
                width: 100,
                minWidth: 100,
                type: 'numericType'
            },
            {
                headerName: 'Cost / Hectare',
                field: 'areaCost',
                width: 100,
                minWidth: 100,
                type: 'numericType'
            },
            {
                headerName: 'Cost / Unit',
                field: 'unitCost',
                width: 100,
                minWidth: 100,
                type: 'numericType'
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    public setupRows() {
        this.expenseService.find(this.parcelSeasonModel.id).subscribe(expenses => {
            this.models = this.expenseService.getTotalModels(expenses);
            this.adjustModels();
            this.options.api.setRowData(this.models);
        });
    }

    private adjustModels() {
        const area = this.parcelSeasonModel.area;
        const yieldGoal = this.parcelSeasonModel.yieldGoal;
        this.models.forEach(model => {
            model.areaCost = NumericUtil.safeDiv(model.cost, area);
            model.unitCost = NumericUtil.safeDiv(model.cost, yieldGoal);
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
