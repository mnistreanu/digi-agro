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
import {ExpenseCategoryService} from '../../../services/expenses/expense-category.service';
import {ExpenseCategoryModel} from '../../enterprise/manage-expense-categories/expense-category/expense-category.model';
import {SelectorItem} from '../../../modules/aggrid/grouped-selector/selector-item.interface';
import {ExpenseService} from '../../../services/expenses/expense.service';
import {AlertService} from '../../../services/alert.service';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {CropSeasonListModel} from '../../manage-crops/crop-season/list/crop-season-list.model';

@Component({
    selector: 'app-expense-list-new',
    templateUrl: './expense-list.component.html',
    styleUrls: ['./expense-list.component.scss']
})
export class ExpenseListComponent implements OnInit {

    options: GridOptions;
    context;

    models: ExpenseModel[];
    categoryModels: ExpenseCategoryTotalModel[];

    confirmationModalId = 'expense-item-remove-confirmation-modal';
    currentModel: ExpenseModel;

    cropSeasons: CropSeasonListModel[];
    selectedCropSeasonId;
    hasCropSeasons: boolean;

    expenseTypes: GroupedSelectorItem[];
    expenseTypeMap: Map<number, String>;

    constructor(private modalService: ModalService,
                private cropSeasonService: CropSeasonService,
                private expenseService: ExpenseService,
                private expenseCategoryService: ExpenseCategoryService,
                private alertService: AlertService,
                private langService: LangService) {
    }


    ngOnInit() {
        this.setupCropSeasons()
            .then(() => this.setupExpenseTypes())
            .then(() => this.setupGrid());
    }

    private setupCropSeasons(): Promise<void> {
        return new Promise((resolve) => {
            this.cropSeasonService.find().subscribe(models => {
                this.cropSeasonService.adjustListModels(models);
                this.cropSeasons = models;
                if (models.length > 0) {
                    this.selectedCropSeasonId = models[0].id;
                    this.hasCropSeasons = true;
                }
                resolve();
            });
        });
    }

    private setupExpenseTypes(): Promise<void> {
        return new Promise((resolve) => {
            this.expenseCategoryService.getTree().subscribe(payloadModel => {
                const models = payloadModel.payload;
                this.expenseTypes = [];
                this.expenseTypeMap = <Map<number, String>>{};
                const leafParent: GroupedSelectorItem = <GroupedSelectorItem> {groupName: '', items: []};
                models.forEach((model: ExpenseCategoryModel) => {
                    const leaf = !model.children || model.children.length === 0;
                    if (leaf) {
                        this.setupSelectorItem(model, leafParent);
                    }
                    else {
                        const parent: GroupedSelectorItem = <GroupedSelectorItem> {groupName: model.name, items: []};
                        this.expenseTypes.push(parent);
                        model.children.forEach(childModel => {
                            this.setupSelectorItem(childModel, parent);
                        });
                    }
                });

                if (leafParent.items.length > 0) {
                    this.expenseTypes.unshift(leafParent);
                }
                resolve();
            });
        });
    }

    private setupSelectorItem(model: ExpenseCategoryModel, parent: GroupedSelectorItem) {
        this.expenseTypeMap[model.id] = model.name;
        const item: SelectorItem = <SelectorItem> {id: model.id, text: model.name};
        parent.items.push(item);
    }

    public onCropSeasonChange() {
        this.setupRows();
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
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
            },
            {
                headerName: 'Type',
                field: 'categoryName',
                width: 100,
                minWidth: 100,
                editable: true,
                cellEditor: 'groupedSelector',
                cellEditorParams: {
                    getDropDownItems: () => this.expenseTypes,
                    dropDownValueField: 'categoryId'
                },
                valueSetter: (params) => this.typeSetter(params),
                onCellValueChanged: (params) => this.onCategoryChange(params)
            },
            {
                headerName: 'Title',
                field: 'title',
                width: 100,
                minWidth: 100,
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
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
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
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

        model.categoryId = value;
        model.categoryName = this.expenseTypeMap[value];
    }

    public setupRows() {
        this.expenseService.find(this.selectedCropSeasonId).subscribe(models => {
            models.forEach(model => model.date = new Date(model.date));
            this.options.api.setRowData(models);
            this.models = models;
            this.buildCategoryModels();
        });
    }

    private buildCategoryModels() {
        this.categoryModels = [];
        const categoryMap = {};
        this.models.forEach((model: ExpenseModel) => {
            let categoryModel = categoryMap[model.categoryName];
            if (!categoryModel) {
                categoryModel = new ExpenseCategoryTotalModel();
                categoryMap[model.categoryName] = categoryModel;
                this.categoryModels.push(categoryModel);

                categoryModel.categoryName = model.categoryName;
                categoryModel.cost = 0;
            }

            categoryModel.cost += model.cost || 0;
        });
        if (this.categoryModels.length === 0) {
            this.categoryModels = null;
        }
    }

    public onDelete(node) {
        this.modalService.open(this.confirmationModalId);
        this.currentModel = node.data;
    }

    public remove() {
        this.expenseService.remove(this.currentModel).subscribe(() => {
            this.options.api.updateRowData({remove: [this.currentModel]});
            this.models.splice(this.models.indexOf(this.currentModel), 1);
            this.currentModel = null;
            this.buildCategoryModels();
            this.alertService.removed();
        });
    }

    public add() {
        let model = this.buildModel();

        this.expenseService.save(model).subscribe(responseModel => {
            model = responseModel;
            model.date = new Date(model.date);

            this.models.push(model);
            this.buildCategoryModels();

            this.options.api.updateRowData({add: [model]});
            this.alertService.saved();
        });
    }

    private buildModel() {
        const keys = Object.keys(this.expenseTypeMap);

        const model = new ExpenseModel();
        model.cropSeasonId = this.selectedCropSeasonId;
        model.date = new Date();
        model.categoryId = +keys[0];
        model.categoryName = this.expenseTypeMap[keys[0]];
        model.cost = 0;

        return model;
    }

    private onCategoryChange(params) {
        this.buildCategoryModels();
        this.updateModel(params.data);
    }

    private onCostChange(params) {
        this.buildCategoryModels();
        this.updateModel(params.data);
    }

    private updateModel(model) {
        this.expenseService.save(model).subscribe(() => {
            this.alertService.saved();
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
