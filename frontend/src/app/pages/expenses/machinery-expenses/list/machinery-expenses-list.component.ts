import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {Router} from '@angular/router';
import {MachineryExpenseService} from '../../../../services/expenses/machinery-expense.service';
import {DateUtil} from '../../../../common/dateUtil';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AuthService} from '../../../../services/auth/auth.service';
import {Authorities} from '../../../../common/authorities';

@Component({
    selector: 'app-machinery-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './machinery-expenses-list.component.html',
    styleUrls: ['./machinery-expenses-list.component.scss']
})
export class MachineryExpensesListComponent implements OnInit {

    readOnly;

    options: GridOptions;
    context;

    constructor(private machineryExpenseService: MachineryExpenseService,
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
                headerName: 'machine.spare-part',
                field: 'sparePart',
                width: 200,
                minWidth: 200,
            },
            {
                headerName: 'machine.spare-part-price',
                field: 'sparePartPrice',
                width: 80,
                minWidth: 80,
                suppressFilter: true,
            },
            {
                headerName: 'info.registered-by',
                hide: true,
                field: 'createdAt',
                width: 100,
                minWidth: 100,
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'info.registered-at',
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
        this.machineryExpenseService.find().subscribe(models => {
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
        this.router.navigate(['/pages/expenses/machinery/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/expenses/machinery/' + model.expenseId]);
    }
}
