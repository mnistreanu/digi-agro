import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {AuthService} from '../../../services/auth/auth.service';
import {Router} from '@angular/router';
import {LangService} from '../../../services/lang.service';
import {DateUtil} from '../../../common/dateUtil';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {Authorities} from '../../../common/authorities';
import {ExpenseService} from '../../../services/expenses/expense.service';
import {ExpenseListModel} from './expense-list.model';
@Component({
    selector: 'app-expense-list',
    templateUrl: './expense-list.component.html',
    styleUrls: ['./expense-list.component.scss']
})
export class ExpenseListComponent implements OnInit {

    readOnly;

    options: GridOptions;
    context;

    constructor(private expenseService: ExpenseService,
                private authService: AuthService,
                private router: Router,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupTableMode();
        this.setupGrid();
    }

    private setupTableMode() {
        this.readOnly = this.authService.hasAuthority(Authorities.ROLE_USER);
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
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
                headerName: 'expenses.date',
                field: 'expenseDate',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
            },
            {
                headerName: 'machine.agricultural-machinery',
                field: 'machine',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'employee.employee',
                field: 'employee',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            // {
            //     headerName: 'expense-category.name',
            //     field: 'category',
            //     width: 200,
            //     minWidth: 200,
            //     suppressFilter: true,
            // },
            // {
            //     headerName: 'expenses.element',
            //     field: 'element',
            //     width: 200,
            //     minWidth: 200,
            //     suppressFilter: true,
            // },
            // {
            //     headerName: 'expenses.quantity',
            //     field: 'quantity',
            //     width: 100,
            //     minWidth: 100,
            //     suppressFilter: true,
            // },
            // {
            //     headerName: 'expenses.cost',
            //     field: 'cost',
            //     width: 100,
            //     minWidth: 100,
            //     suppressFilter: true,
            // },
            {
                headerName: 'info.created-by',
                field: 'createdBy',
                width: 100,
                minWidth: 100,
            },
            {
                headerName: 'info.creation-time',
                field: 'createdAt',
                width: 130,
                minWidth: 130,
                valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
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
        this.expenseService.find().subscribe(models => {
            models = this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustModels(models) {
        const resultModels = [];
        const modelMap = {};
        const columnMap = {};
        const columns = [];
        const expenseGroups = this.groupByExpense(models);
        Object.keys(expenseGroups).forEach((expenseId) => {
            let group = expenseGroups[expenseId];
            group = this.aggregateSimilarCategories(group);
            group.forEach(groupModel => {
                this.transformCategoryModel(groupModel, resultModels, columns, modelMap, columnMap);
            });
        });

        this.registerCategoryColumns(columns);

        return resultModels;
    }

    private groupByExpense(models: ExpenseListModel[]) {
        const expenseGroups = {};

        models.forEach(model => {
            let group = expenseGroups[model.expenseId];
            if (!group) {
                group = [];
                expenseGroups[model.expenseId] = group;
            }
            group.push(model);
        });

        return expenseGroups;
    }

    private aggregateSimilarCategories(models: ExpenseListModel[]) {
        const categoryModels = {};
        const resultModels = [];

        models.forEach(model => {
            const categoryModel = categoryModels[model.categoryId];
            if (categoryModel) {
                const fields = ['quantity', 'cost'];
                fields.forEach(field => {
                   categoryModel[field] = categoryModel[field] || 0;
                   categoryModel[field] += model[field] || 0;
                });
            }
            else {
                categoryModels[model.categoryId] = model;
                resultModels.push(model);
            }
        });

        return resultModels;
    }

    private transformCategoryModel(model, models, columns, modelMap, columnMap) {
        const parentCategoryId = 'p-' + (model.parentCategoryId == null ? model.categoryId : model.parentCategoryId);
        const childCategoryId = parentCategoryId + '-c-' + model.categoryId;

        if (!modelMap[parentCategoryId]) {
            modelMap[parentCategoryId] = model;
            models.push(model);
        }

        const aggregatedModel = modelMap[parentCategoryId];

        const quantityField = 'quantity_' + model.categoryId;
        const costField = 'cost_' + model.categoryId;

        aggregatedModel[quantityField] = model.quantity;
        aggregatedModel[costField] = model.cost;


        let parentColumn = columnMap[parentCategoryId];

        if (!parentColumn) {
            parentColumn = {
                headerName: model.parentCategory,  // todo: replace with parentCategory
                width: 180,
                minWidth: 180,
                children: []
            };
            columnMap[parentCategoryId] = parentColumn;
            columns.push(parentColumn);
        }

        let childColumn = columnMap[childCategoryId];
        if (!childColumn) {

            const quantityTitle = this.langService.instant('expenses.quantity');
            const costTitle = this.langService.instant('expenses.cost');

            childColumn = {
                headerName: model.category,
                width: 180,
                minWidth: 180,
                columnGroupShow: parentColumn.children.length == 0 ? 'close' : 'open',
                children: [
                    {
                        headerName: quantityTitle,
                        field: quantityField,
                        width: 180,
                        minWidth: 180,
                        columnGroupShow: 'close'
                    },
                    {
                        headerName: costTitle,
                        field: costField,
                        width: 180,
                        minWidth: 180,
                        columnGroupShow: 'open'
                    }
                ]
            };
            columnMap[childCategoryId] = childColumn;
            parentColumn.children = parentColumn.children.concat(childColumn);
        }
    }

    private registerCategoryColumns(columns) {
        let gridColumns = this.options.columnDefs;
        let index = 0;
        for (const column of gridColumns) {
            if (column['field'] == 'createdBy') {
                break;
            }
            index++;
        }
        gridColumns = gridColumns.slice(0, index).concat(columns).concat(gridColumns.slice(index));
        this.options.api.setColumnDefs(gridColumns);
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

    public add() {
        // this.router.navigate(['/pages/expenses/machinery/-1']);
    }

    public onEdit(node) {
        // const model = node.data;
        // this.router.navigate(['/pages/expenses/machinery/' + model.expenseId]);
    }
}
