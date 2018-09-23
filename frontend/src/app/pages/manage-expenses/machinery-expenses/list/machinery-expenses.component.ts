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
    templateUrl: './machinery-expenses.component.html',
    styleUrls: ['./machinery-expenses.component.scss']
})
export class MachineryExpensesComponent implements OnInit {

    readOnly;

    options: GridOptions;
    context;

    labelMachineType: string;
    labelAgriculturalMachinery: string;
    labelIdentifier: string;
    labelDate: string;
    labelFirstName: string;
    labelLastName: string;
    labelSparePart: string;
    labelSparePartPrice: string;

    constructor(private machineryExpenseService: MachineryExpenseService,
                private authService: AuthService,
                private router: Router,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupTableMode();
        this.setupLabels();
        this.setupGrid();
    }

    private setupTableMode() {
        this.readOnly = this.authService.hasAuthority(Authorities.ROLE_USER);
    }

    private setupLabels() {
        this.langService.get('machine.type').subscribe(msg => this.labelMachineType = msg);
        this.langService.get('machine.agricultural-machinery').subscribe(msg => this.labelAgriculturalMachinery = msg);
        this.langService.get('machine.identifier').subscribe(msg => this.labelIdentifier = msg);
        this.langService.get('machine.repairing-date').subscribe(msg => this.labelDate = msg);
        this.langService.get('employee.first-name').subscribe(msg => this.labelFirstName = msg);
        this.langService.get('employee.last-name').subscribe(msg => this.labelLastName = msg);
        this.langService.get('machine.spare-part').subscribe(msg => this.labelSparePart = msg);
        this.langService.get('machine.spare-part-price').subscribe(msg => this.labelSparePartPrice = msg);
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
                headerName: this.labelDate,
                field: 'expenseDate',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
            },
            {
                headerName: this.labelAgriculturalMachinery,
                field: 'machine',
                width: 180,
                minWidth: 180
            },
            {
                headerName: this.labelLastName + ' ' + this.labelFirstName,
                field: 'employee',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: this.labelSparePart,
                field: 'sparePart',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: this.labelSparePartPrice,
                field: 'sparePartPrice',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'Registered By',
                field: '',
                width: 100,
                minWidth: 100,
            },
            {
                headerName: 'At',
                field: '',
                width: 60,
                minWidth: 60,
            }
        ]);

        return headers;
    }


    public setupRows() {
        this.machineryExpenseService.find().subscribe(models => {
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
