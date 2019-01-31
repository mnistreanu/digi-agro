import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {DateUtil} from '../../../common/dateUtil';
import {LangService} from '../../../services/lang.service';
import {ExpenseModel} from '../models/expense.model';
import {ExpenseCategoryModel} from '../../enterprise/manage-expense-categories/expense-category/expense-category.model';
import {AgDateColumnType} from '../../../modules/aggrid/column-types/ag-date-type';

@Component({
    selector: 'app-expense-list-new',
    templateUrl: './expense-list-new.component.html',
    styleUrls: ['./expense-list-new.component.scss']
})
export class ExpenseListNewComponent implements OnInit {

    options: GridOptions;
    context;

    models: ExpenseModel[];
    categoryModels: ExpenseCategoryModel[];

    constructor(private langService: LangService) {
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

        this.setupRows();
    }

    private setupColumnTypes() {
        this.options.columnTypes = {
            dateType: AgDateColumnType.getType()
        };
    }

    private setupHeaders() {

        let headers: ColDef[] = [];


        headers = headers.concat([
            {
                headerName: 'expenses.date',
                field: 'date',
                type: 'dateType',
                editable: true
            },
            {
                headerName: 'Type',
                field: 'type',
                width: 100
            },
            {
                headerName: 'Title',
                field: 'title',
                width: 100
            },
            {
                headerName: 'Description',
                field: 'description',
                width: 100
            },
            {
                headerName: 'Cost',
                field: 'cost',
                width: 100
            }
        ]);

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }


    public setupRows() {
        setTimeout(() => {
            this.models = this.getDummyData();
            this.options.api.setRowData(this.models);
            this.buildCategoryModels();
        });
    }

    private getDummyData(): ExpenseModel[] {
        return <ExpenseModel[]>[
            {date: new Date('2018-12-12'), type: 'Seed', title: 'Seed 001', cost: 5000},
            {date: new Date('2018-12-13'), type: 'Seed', title: 'Seed 002', cost: 1000},
            {date: new Date('2018-12-13'), type: 'Herbicide', title: 'Herbicide 001', cost: 5000},
            {date: new Date('2018-12-23'), type: 'Fertilizer', title: 'Fertilizer 001', cost: 4200},
            {date: new Date('2018-12-10'), type: 'Equipment', title: 'Equipment 001', description: 'some description...', cost: 3000},
        ];
    }

    private buildCategoryModels() {
        this.categoryModels = [];
        const categoryMap = {};
        this.models.forEach((model: ExpenseModel) => {
            let categoryModel = categoryMap[model.type];
            if (!categoryModel) {
                categoryModel = new ExpenseCategoryModel();
                categoryMap[model.type] = categoryModel;
                this.categoryModels.push(categoryModel);

                categoryModel.type = model.type;
                categoryModel.cost = 0;
            }

            categoryModel.cost += model.cost || 0;
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
