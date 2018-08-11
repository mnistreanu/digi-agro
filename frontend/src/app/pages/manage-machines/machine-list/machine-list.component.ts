import {Component, OnInit} from '@angular/core';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ColDef, GridOptions} from 'ag-grid';
import {MachineService} from '../../../services/machine.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-machine-list',
    templateUrl: './machine-list.component.html',
    styleUrls: ['./machine-list.component.scss']
})
export class MachineListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
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
                headerName: 'Identifier',
                field: 'identifier',
                width: 200,
                minWidth: 200,
                maxWidth: 200
            },
            {
                headerName: 'Name',
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Owner',
                field: 'owner',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Type',
                field: 'type',
                width: 200,
                minWidth: 200
            }
        ];

        return headers;
    }

    private setupRows() {
        this.machineService.findAll().subscribe(models => {
            this.options.api.setRowData(models);
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            this.options.api.sizeColumnsToFit();
        }, 500);
    }


    public add() {
        this.router.navigate(['/pages/manage-machines/machine/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/manage-machines/machine/' + model.id]);
    }

}
