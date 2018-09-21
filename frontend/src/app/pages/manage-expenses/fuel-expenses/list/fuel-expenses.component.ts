import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {Router} from '@angular/router';
import {LangService} from '../../../../services/lang.service';
import {DateUtil} from '../../../../common/dateUtil';
import { PinnedRowRendererComponent } from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {AuthService} from '../../../../services/auth/auth.service';
import {FuelExpenseService} from '../../../../services/fuel-expense.service';
import {Authorities} from '../../../../common/authorities';

@Component({
    selector: 'app-fuel-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './fuel-expenses.component.html',
    styleUrls: ['./fuel-expenses.component.scss']
})
export class FuelExpensesComponent implements OnInit {
    readOnly;

    options: GridOptions;
    context;

    labelMachineType: string;
    labelAgriculturalMachinery: string;
    labelIdentifier: string;
    labelDate: string;
    labelFirstName: string;
    labelLastName: string;
    labelUnitOfMeasureShort: string;
    labelUnitOfMeasureLong: string;
    labelDiesel: string;
    labelOil: string;
    labelSolidol: string;
    labelNegrol: string;

    constructor(private fuelExpenseService: FuelExpenseService,
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
        this.langService.get('unit-of-measure.unit-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
        this.langService.get('unit-of-measure.unit-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
        this.langService.get('fuel.diesel').subscribe(msg => this.labelDiesel = msg);
        this.langService.get('fuel.oil').subscribe(msg => this.labelOil = msg);
        this.langService.get('fuel.solidol').subscribe(msg => this.labelSolidol = msg);
        this.langService.get('fuel.negrol').subscribe(msg => this.labelNegrol = msg);
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

        const headers: ColDef[] = [
            {
                headerName: this.labelDate,
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } },
                valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
            },
            {
                headerName: this.labelAgriculturalMachinery,
                field: 'brandModel',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelIdentifier,
                field: 'identifier',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelLastName  + ' ' + this.labelFirstName,
                field: 'employee',
                width: 140,
                minWidth: 140,
                suppressFilter: true,
            },
            {
                headerName: this.labelUnitOfMeasureShort,
                headerTooltip: this.labelUnitOfMeasureLong,
                field: 'unitOfMeasure',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelDiesel,
                field: 'diesel',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                // allow gui to set aggregations for this column
//                enableValue: true,
                // restrict aggregations to sum
//                allowedAggFuncs: ['sum'],
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } }
            },
            {
                headerName: this.labelOil,
                field: 'oil',
                width: 80,
                minWidth: 80,
                suppressFilter: true,
            },
            {
                headerName: this.labelSolidol,
                field: 'solidol',
                width: 80,
                minWidth: 80,
                suppressFilter: true,
            },
            {
                headerName: this.labelNegrol,
                field: 'negrol',
                width: 80,
                minWidth: 80,
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
            },

        ];


        return headers;
    }

    public setupRows() {
        this.fuelExpenseService.find().subscribe(models => {
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
