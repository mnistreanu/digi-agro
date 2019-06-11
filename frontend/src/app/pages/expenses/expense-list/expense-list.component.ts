import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {ExpenseModel} from '../models/expense.model';
import {AgDateColumnType} from '../../../modules/aggrid/column-types/ag-date-type';
import {AgNumericColumnType} from '../../../modules/aggrid/column-types/ag-numeric-type';
import {AgDeleteColumnType} from '../../../modules/aggrid/column-types/ag-delete-type';
import {ModalService} from '../../../services/modal.service';
import {ExpenseCategoryService} from '../../../services/expenses/expense-category.service';
import {ExpenseCategoryModel} from '../../enterprise/manage-expense-categories/expense-category/expense-category.model';
import {ExpenseService} from '../../../services/expenses/expense.service';
import {AlertService} from '../../../services/alert.service';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {CropSeasonListModel} from '../../manage-crops/crop-season/list/crop-season-list.model';
import {SelectorComponent} from '../../../modules/aggrid/selector/single-selector/selector.component';
import {ExpenseSummaryModel} from '../models/expense-summary.model';

@Component({
    selector: 'app-expense-list',
    templateUrl: './expense-list.component.html',
    styleUrls: ['./expense-list.component.scss']
})
export class ExpenseListComponent implements OnInit, OnDestroy {

    @Input() readOnlyMode = false;
    @Input() singleSeasonMode = false;
    @Input() shortMode = false;
    @Input() cropSeasonId;

    options: GridOptions;
    context;

    models: ExpenseModel[];
    summaryModels: ExpenseSummaryModel[];

    confirmationModalId = 'expense-item-remove-confirmation-modal';
    currentModel: ExpenseModel;

    seasonYearSubscription;
    cropSeasons: CropSeasonListModel[];
    hasCropSeasons: boolean;

    categoryMap: Map<number, ExpenseCategoryModel>;
    rootCategories: ExpenseCategoryModel[];

    constructor(private modalService: ModalService,
                private cropSeasonService: CropSeasonService,
                private expenseService: ExpenseService,
                private expenseCategoryService: ExpenseCategoryService,
                private alertService: AlertService,
                private langService: LangService) {
    }


    ngOnInit() {
        this.setupCropSeasons()
            .then(() => this.setupCategories())
            .then(() => this.setupGrid());

        if (this.singleSeasonMode) {
            this.hasCropSeasons = this.cropSeasonId != null;
        } else {
            this.seasonYearSubscription = this.cropSeasonService.seasonYearChanged.subscribe(() => {
                this.setupCropSeasons()
                    .then(() => this.setupRows());
            });
        }
    }

    ngOnDestroy() {
        if (this.seasonYearSubscription) {
            this.seasonYearSubscription.unsubscribe();
        }
    }

    private setupCropSeasons(): Promise<void> {
        return new Promise((resolve) => {
            if (this.singleSeasonMode) {
                resolve();
            }
            this.cropSeasonService.findByYear(this.cropSeasonService.seasonYear).subscribe(models => {
                this.cropSeasonService.adjustListModels(models);
                this.cropSeasons = models;
                if (models.length > 0) {
                    this.cropSeasonId = models[0].id;
                    this.hasCropSeasons = true;
                }
                resolve();
            });
        });
    }

    private setupCategories(): Promise<void> {
        return new Promise((resolve) => {
            this.expenseCategoryService.getTree().subscribe(payloadModel => {
                const models = payloadModel.payload;
                this.categoryMap = <Map<number, ExpenseCategoryModel>>{};
                this.rootCategories = [];
                models.forEach((model: ExpenseCategoryModel) => {
                    this.rootCategories.push(model);
                    this.categoryMap[model.id] = model;

                    if (model.children) {
                        model.children.forEach((child) => {
                            this.categoryMap[child.id] = child;
                        });
                    }
                });

                resolve();
            });
        });
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
        this.options.columnDefs = this.setupColumns();
        this.context = {componentParent: this};
        this.options.frameworkComponents = {
            selectorEditor: SelectorComponent
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

    private setupColumns() {
        let columns: ColDef[] = <ColDef[]>[
            {
                type: 'deleteType',
                hide: this.readOnlyMode
            },
            {
                headerName: 'expenses.date',
                field: 'date',
                type: 'dateType',
                editable: !this.readOnlyMode,
                onCellValueChanged: (params) => this.updateModel(params.data),
                hide: this.shortMode
            },
            {
                headerName: 'Category',
                field: 'categoryId',
                width: 100,
                minWidth: 100,
                editable: !this.readOnlyMode,
                cellEditor: 'selectorEditor',
                cellEditorParams: {
                    getDropDownItems: () => this.getCategoriesDropDownItems(),
                    dropDownValueField: 'categoryId'
                },
                valueFormatter: (params) => this.categoryFormatter(params),
                onCellValueChanged: (params) => this.onCategoryChange(params)
            },
            {
                headerName: 'Detail',
                field: 'subCategoryId',
                width: 100,
                minWidth: 100,
                editable: !this.readOnlyMode,
                cellEditor: 'selectorEditor',
                cellEditorParams: {
                    getDropDownItems: (params) => this.getSubCategoriesDropDownItems(params),
                    dropDownValueField: 'subCategoryId'
                },
                valueFormatter: (params) => this.categoryFormatter(params),
                onCellValueChanged: (params) => this.onSubCategoryChange(params)
            },
            {
                headerName: 'Cost',
                field: 'cost',
                width: 100,
                minWidth: 100,
                type: 'numericType',
                editable: !this.readOnlyMode,
                onCellValueChanged: (params) => this.onCostChange(params)
            },
            {
                headerName: 'Title',
                field: 'title',
                width: 100,
                minWidth: 100,
                editable: !this.readOnlyMode,
                onCellValueChanged: (params) => this.updateModel(params.data),
                hide: this.shortMode
            },
            {
                headerName: 'Description',
                field: 'description',
                width: 200,
                minWidth: 100,
                editable: !this.readOnlyMode,
                onCellValueChanged: (params) => this.updateModel(params.data),
                hide: this.shortMode
            }
        ];

        columns = columns.filter(h => !h.hide);

        columns.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return columns;
    }

    private createDropDownItems(list: ExpenseCategoryModel[]) {
        if (!list) {
            return [];
        }
        return list.map(item => {
            return {
                id: item.id,
                text: item.name
            };
        });
    }

    private getCategoriesDropDownItems() {
        return this.createDropDownItems(this.rootCategories);
    }

    private getSubCategoriesDropDownItems(model) {
        return this.createDropDownItems(this.categoryMap[model.categoryId].children);
    }

    private categoryFormatter(params) {
        const item = this.categoryMap[params.value];
        return item ? item.name : null;
    }

    public setupRows() {
        if (!this.hasCropSeasons) {
            return;
        }
        this.expenseService.find(this.cropSeasonId).subscribe(models => {
            models.forEach(model => model.date = new Date(model.date));
            this.options.api.setRowData(models);
            this.models = models;
            this.buildSummaryModels();
        });
    }

    private buildSummaryModels() {
        this.summaryModels = this.expenseService.convertSummaryModels(this.models);
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
            this.buildSummaryModels();
            this.alertService.removed();
        });
    }

    public add() {
        let model = this.buildModel();

        this.expenseService.save(model).subscribe(responseModel => {
            model = responseModel;
            model.date = new Date(model.date);

            this.models.push(model);
            this.buildSummaryModels();

            this.options.api.updateRowData({add: [model]});
            this.alertService.saved();
        });
    }

    private buildModel() {
        const keys = Object.keys(this.categoryMap);
        const categoryId = +keys[0];

        const model = new ExpenseModel();
        model.cropSeasonId = this.cropSeasonId;
        model.date = new Date();
        model.categoryId = categoryId;
        model.categoryName = this.categoryMap[categoryId].name;
        model.cost = 0;

        return model;
    }

    private onCategoryChange(params) {
        const model = params.data;
        model.categoryName = this.categoryMap[model.categoryId].name;

        if (model.subCategoryId != null) {
            const subCategory = this.categoryMap[model.subCategoryId];
            if (subCategory.parentId != model.categoryId) {
                model.subCategoryId = null;
                model.subCategoryName = null;

                params.api.updateRowData({update: [model]});
            }
        }

        this.buildSummaryModels();
        this.updateModel(model);
    }

    private onSubCategoryChange(params) {
        const model = params.data;
        model.subCategoryName = this.categoryMap[model.subCategoryId].name;
        this.updateModel(model);
    }

    private onCostChange(params) {
        this.buildSummaryModels();
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
