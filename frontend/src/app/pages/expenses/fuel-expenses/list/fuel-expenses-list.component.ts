import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {Router} from '@angular/router';
import {LangService} from '../../../../services/lang.service';
import {DateUtil} from '../../../../common/dateUtil';
import {PinnedRowRendererComponent} from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {AuthService} from '../../../../services/auth/auth.service';
import {FuelExpenseService} from '../../../../services/expenses/fuel-expense.service';
import {Authorities} from '../../../../common/authorities';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';

@Component({
    selector: 'app-fuel-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './fuel-expenses-list.component.html',
    styleUrls: ['./fuel-expenses-list.component.scss']
})
export class FuelExpensesComponent implements OnInit {
    readOnly;

    options: GridOptions;
    context;

    constructor(private fuelExpenseService: FuelExpenseService,
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
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = {customPinnedRowRenderer: PinnedRowRendererComponent};

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
                headerName: 'machine.repairing-date',
                field: 'expenseDate',
                width: 100,
                minWidth: 100,
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'machine.agricultural-machinery',
                field: 'machinesString',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'employee.first-last-name',
                field: 'employeesString',
                width: 150,
                minWidth: 150,
            },
            {
                headerName: 'info.registered-at',
                hide: true,
                field: 'createdAt',
                width: 100,
                minWidth: 100,
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'info.registered-by',
                hide: true,
                field: 'createdBy',
                width: 100,
                minWidth: 100,
            }

        ]);

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }

    public setupRows() {
        this.fuelExpenseService.find().subscribe(models => {
            const dynamicFields = this.transformModels(models);
            models.forEach(model => {
                model.employeesString = model.employees.map(employee => employee.firstName + ' ' + employee.lastName).join(', ');
                model.machinesString = model.machines
                    .map(machine => machine.brand + ' ' + machine.model + ' (' + machine.identifier + ')').join(', ');
            });
            this.options.api.setRowData(models);
            this.setupSummaryRow(models, dynamicFields);
        });
    }

    private transformModels(expenseModels) {

        const fuelMap = {};

        expenseModels.forEach(expenseModel => {
            expenseModel.fuels.forEach(fuelModel => {
                expenseModel[fuelModel.category] = (expenseModel[fuelModel.category] || 0) + (fuelModel.quantity || 0);
                fuelMap[fuelModel.category] = fuelModel.category;
            });
        });

        const fuelFields = Object.keys(fuelMap);
        this.registerFuelColumns(fuelFields);
        return fuelFields;
    }

    private registerFuelColumns(fuelFields) {

        let gridColumns = this.options.columnDefs;
        const index = this.getFuelStartColumnIndex();
        const columns = [];

        fuelFields.forEach(fuel => {
            const fuelColumn = {
                headerName: fuel,
                field: fuel,
                width: 180,
                minWidth: 180,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { fontWeight: 'bold' } }
            };
            columns.push(fuelColumn);
        });

        gridColumns = gridColumns.slice(0, index).concat(columns).concat(gridColumns.slice(index));
        this.options.api.setColumnDefs(gridColumns);
    }

    private getFuelStartColumnIndex() {
        const gridColumns = this.options.columnDefs;
        let index = 0;
        for (const column of gridColumns) {
            if (column['field'] == 'createdAt') {
                break;
            }
            index++;
        }
        return index;
    }

    private setupSummaryRow(models, dynamicFields) {
        const summaryRow = {};
        models.forEach(source => {
            dynamicFields.forEach(field => {
                summaryRow[field] = (summaryRow[field] || 0) + (source[field] || 0);
            });
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

    public add() {
        this.router.navigate(['/pages/expenses/fuel/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/expenses/fuel/' + model.expenseId]);
    }
}
