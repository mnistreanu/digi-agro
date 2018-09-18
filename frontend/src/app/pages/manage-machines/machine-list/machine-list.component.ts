import {Component, OnInit} from '@angular/core';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ColDef, GridOptions} from 'ag-grid';
import {MachineService} from '../../../services/machine.service';
import {ActivatedRoute, Router} from '@angular/router';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-machine-list',
    templateUrl: './machine-list.component.html',
    styleUrls: ['./machine-list.component.scss']
})
export class MachineListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private machineService: MachineService) {
    }

    ngOnInit() {
        this.setupGrid();
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

        const headers: ColDef[] = [
            {
                field: 'edit',
                width: 24,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: 'machine.type',
                field: 'type',
                width: 175,
                minWidth: 175
            },
            {
                headerName: 'machine.model',
                field: 'brandAndModel',
                width: 175,
                minWidth: 175
            },
            {
                headerName: 'machine.identifier',
                field: 'identifier',
                width: 100,
                minWidth: 100,
                maxWidth: 100
            },
            {
                headerName: 'machine.responsible-persons',
                field: 'responsiblePersons',
                width: 300,
                minWidth: 300
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.machineService.findAll().subscribe(models => {
            models.forEach((model) => {
                model.type = this.langService.instant('machine-type.' + model.type);
                model['brandAndModel'] = model.brand + ' ' + model.model;
                model['responsiblePersons'] = model.employees.map(m => m.firstName + ' ' + m.lastName).join(', ');
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
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }

}
