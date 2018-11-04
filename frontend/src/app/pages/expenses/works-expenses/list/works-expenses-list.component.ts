import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, ColGroupDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {MachineService} from '../../../../services/machine.service';
import {WorksExpensesListModel} from './works-expenses-list.model';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {WorksExpenseService} from '../../../../services/expenses/works-expense.service';
import {DateUtil} from '../../../../common/dateUtil';

@Component({
    selector: 'app-works-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './works-expenses-list.component.html',
    styleUrls: ['./works-expenses-list.component.scss']
})
export class WorksExpensesListComponent implements OnInit {
    readOnly;
    options: GridOptions;
    context;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private machineService: MachineService,
                private worksExpenseService: WorksExpenseService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupGrid();
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

        this.setupRows();
    }

    private setupHeaders() {

        let headers: (ColDef | ColGroupDef) [] = [];

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
                valueFormatter: (params) => DateUtil.formatDate(params.value)
            },
            {
                headerName: 'agro-work.type',
                field: 'workType',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'crop.name',
                field: 'crop',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'unit-of-measure.unit-short',
                headerTooltip: 'unit-of-measure.unit-long',
                field: 'unitOfMeasure',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.quantity',
                headerTooltip: 'expenses.quantity',
                field: 'quantity',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.norm',
                headerTooltip: 'expenses.norm',
                field: 'quantityNorm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.defacto',
                headerTooltip: 'expenses.defacto',
                field: 'quantityDefacto',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.price-1-norm',
                headerTooltip: 'expenses.price-1-norm',
                field: 'price1Norm',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'expenses.sum',
                field: 'sum',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: 'machine.agricultural-machinery',
                field: 'machinesString',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'employee.first-last-name' ,
                field: 'employeesString',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
            },
            {
                headerName: 'info.registered',
                children: [
                    {
                        headerName: 'info.by',
                        field: 'createdBy',
                        width: 100,
                        minWidth: 100,
                    },
                    {
                        headerName: 'info.at',
                        field: 'createdAt',
                        width: 90,
                        minWidth: 90,
                        valueFormatter: (params) => DateUtil.formatDate(params.value)
                    },
                ]
            },
        ]);


        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);

                if (header.hasOwnProperty('children')) {
                    header['children'].forEach(childHeader => {
                        if (childHeader.headerName) {
                            this.langService.get(childHeader.headerName).subscribe(m => childHeader.headerName = m);
                        }
                    });
                }
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }


    public setupRows() {
        this.worksExpenseService.find().subscribe(models => {
            models.forEach(model => {
                model.unitOfMeasure = this.langService.instant('unit-of-measure.' + model.unitOfMeasure);

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
        this.router.navigate(['./-1'], {relativeTo: this.route});
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.expenseId], {relativeTo: this.route});
    }
}
