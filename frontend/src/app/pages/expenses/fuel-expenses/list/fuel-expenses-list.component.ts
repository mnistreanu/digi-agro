import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {Router} from '@angular/router';
import {LangService} from '../../../../services/lang.service';
import {DateUtil} from '../../../../common/dateUtil';
import { PinnedRowRendererComponent } from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
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
        this.options.frameworkComponents = { customPinnedRowRenderer: PinnedRowRendererComponent };

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
            // {
            //     headerName: '',
            //     field: 'diesel',
            //     width: 100,
            //     minWidth: 100,
            //     suppressFilter: true,
            //     pinnedRowCellRenderer: 'customPinnedRowRenderer',
            //     pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } }
            // },
            // {
            //     headerName: '',
            //     field: 'oil',
            //     width: 80,
            //     minWidth: 80,
            //     suppressFilter: true,
            // },
            // {
            //     headerName: '',
            //     field: 'solidol',
            //     width: 80,
            //     minWidth: 80,
            //     suppressFilter: true,
            // },
            // {
            //     headerName: '',
            //     field: 'negrol',
            //     width: 80,
            //     minWidth: 80,
            //     suppressFilter: true,
            // },
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

            // if (header.field == 'employeesString') {
            //     headers.push();
            // }
        });

        return headers;
    }

    public setupRows() {
        this.fuelExpenseService.find().subscribe(models => {

            debugger;
            if (models.length > 0) {
                const fuelModels = models[0].fuels;
                if (fuelModels.length > 0) {
                    const fuelColumn = {
                        headerName: fuelModels[0].category,
                        field: fuelModels[0].category,
                        width: 180,
                        minWidth: 180,
                    };

                }
            }


            models.forEach(model => {
                model.employees.forEach(employee => {
                    if (model.employeesString) {
                        model.employeesString = model.employeesString + ', ' + employee.firstName + ' ' + employee.lastName;
                    } else {
                        model.employeesString = employee.firstName + ' ' + employee.lastName;
                    }
                });

                model.machines.forEach(machine => {
                    if (model.machinesString) {
                        model.machinesString = model.machinesString + ', ' + machine.brand + ' ' + machine.model
                            + ' (' + machine.identifier + ')';
                    } else {
                        model.machinesString = machine.brand + ' ' + machine.model + ' (' + machine.identifier + ')';
                    }
                });
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

    public add() {
        this.router.navigate(['/pages/expenses/fuel/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/expenses/fuel/' + model.expenseId]);
    }
}
