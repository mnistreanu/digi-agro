import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {ExpenseModel} from '../models/expense.model';
import {AgDateColumnType} from '../../../modules/aggrid/column-types/ag-date-type';
import {AgNumericColumnType} from '../../../modules/aggrid/column-types/ag-numeric-type';
import {ExpenseCategoryTotalModel} from '../models/expense-category-total.model';
import {AgDeleteColumnType} from '../../../modules/aggrid/column-types/ag-delete-type';
import {ModalService} from '../../../services/modal.service';
import {GroupedSelectorComponent} from '../../../modules/aggrid/grouped-selector/grouped-selector.component';
import {GroupedSelectorItem} from '../../../modules/aggrid/grouped-selector/grouped-selector-item.interface';

@Component({
    selector: 'app-expense-list-new',
    templateUrl: './expense-list-new.component.html',
    styleUrls: ['./expense-list-new.component.scss']
})
export class ExpenseListNewComponent implements OnInit {

    options: GridOptions;
    context;

    models: ExpenseModel[];
    categoryModels: ExpenseCategoryTotalModel[];

    confirmationModalId = 'expense-item-remove-confirmation-modal';
    currentModel: ExpenseModel;

    expenseTypes: GroupedSelectorItem[];
    expenseTypeMap: Map<number, any>;

    constructor(private modalService: ModalService,
                private langService: LangService) {
    }

    // todo: type drop-down

    ngOnInit() {
        this.setupExpenseTypes();
        this.setupGrid();
    }

    private setupExpenseTypes() {
        // todo: need fetch real types...
        this.expenseTypes = <GroupedSelectorItem[]>[
            {
                groupName: 'Combustibil',
                items: [
                    {id: 1, label: 'Motorina'},
                    {id: 2, label: 'Ulei'},
                    {id: 3, label: 'Solidol'},
                ]
            },
            {
                groupName: 'Agro',
                items: [
                    {id: 4, label: 'Seed'}
                ]
            }
        ];

        this.expenseTypeMap = <Map<number, any>>{};
        this.expenseTypeMap[1] = 'Motorina';
        this.expenseTypeMap[2] = 'Ulei';
        this.expenseTypeMap[3] = 'Solidol';
        this.expenseTypeMap[4] = 'Seed';
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
            deleteType: AgDeleteColumnType.getType(),
            dateType: AgDateColumnType.getType(),
            numericType: AgNumericColumnType.getType()
        };
    }

    private setupHeaders() {

        const self = this;
        let headers: ColDef[] = [];

        headers = headers.concat(<ColDef[]>[
            {
                type: 'deleteType'
            },
            {
                headerName: 'expenses.date',
                field: 'date',
                type: 'dateType',
                editable: true
            },
            {
                headerName: 'Type',
                field: 'type',
                width: 100,
                minWidth: 100,
                editable: true,
                cellEditor: 'groupedSelector',
                cellEditorParams: {
                    dropDownItems: this.expenseTypes,
                    dropDownValueField: 'typeId'
                },
                valueSetter: (params) => this.typeSetter(params),
                onCellValueChanged: (params) => this.onCategoryChange(params)
            },
            {
                headerName: 'Title',
                field: 'title',
                width: 100,
                minWidth: 100,
                editable: true
            },
            {
                headerName: 'Cost',
                field: 'cost',
                width: 100,
                minWidth: 100,
                type: 'numericType',
                editable: true,
                onCellValueChanged: (params) => this.onCostChange(params)
            },
            {
                headerName: 'Description',
                field: 'description',
                width: 200,
                minWidth: 100,
                editable: true
            }
        ]);

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private typeSetter(params) {
        const value = params.newValue;
        const model = params.data;

        model.typeId = value;
        model.type = this.expenseTypeMap[value];
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
            {date: new Date('2018-12-12'), typeId: 1, type: 'A', title: 'Title 001', cost: 5000},
            {date: new Date('2018-12-13'), typeId: 1, type: 'A', title: 'Title 002', cost: 1000},
            {date: new Date('2018-12-13'), typeId: 2, type: 'B', title: 'Title 003', cost: 5000},
            {date: new Date('2018-12-23'), typeId: 3, type: 'C', title: 'Title 004', description: 'description', cost: 4200}
        ];
    }

    private buildCategoryModels() {
        this.categoryModels = [];
        const categoryMap = {};
        this.models.forEach((model: ExpenseModel) => {
            let categoryModel = categoryMap[model.type];
            if (!categoryModel) {
                categoryModel = new ExpenseCategoryTotalModel();
                categoryMap[model.type] = categoryModel;
                this.categoryModels.push(categoryModel);

                categoryModel.type = model.type;
                categoryModel.cost = 0;
            }

            categoryModel.cost += model.cost || 0;
        });
    }

    public onDelete(node) {
        this.modalService.open(this.confirmationModalId);
        this.currentModel = node.data;
    }

    public remove() {
        this.options.api.updateRowData({remove: [this.currentModel]});
        this.models.splice(this.models.indexOf(this.currentModel), 1);
        this.currentModel = null;
        this.buildCategoryModels();
    }

    public add() {
        const model = this.buildModel();
        this.models.push(model);
        this.buildCategoryModels();

        this.options.api.updateRowData({add: [model]});
    }

    private buildModel() {
        const model = new ExpenseModel();
        model.date = new Date();
        model.type = 'Seed';
        model.cost = 0;

        return model;
    }

    private onCategoryChange(params) {
        this.buildCategoryModels();
    }

    private onCostChange(params) {
        this.buildCategoryModels();
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
