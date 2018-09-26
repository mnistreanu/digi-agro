import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {AuthService} from '../../../services/auth/auth.service';
import {Router} from '@angular/router';
import {LangService} from '../../../services/lang.service';
import {DateUtil} from '../../../common/dateUtil';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {Authorities} from '../../../common/authorities';
import {ExpenseService} from '../../../services/expenses/expense.service';
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
            {
                headerName: 'expense-category.name',
                field: 'category',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.element',
                field: 'element',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.amount',
                field: 'amount',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.cost',
                field: 'cost',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
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
        // this.router.navigate(['/pages/expenses/machinery/-1']);
    }

    public onEdit(node) {
        // const model = node.data;
        // this.router.navigate(['/pages/expenses/machinery/' + model.expenseId]);
    }
}
