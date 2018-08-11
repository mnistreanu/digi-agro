import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {BrandService} from '../../../services/brand.service';

@Component({
    selector: 'app-brand-list',
    templateUrl: './brand-list.component.html',
    styleUrls: ['./brand-list.component.scss']
})
export class BrandListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private brandService: BrandService) {
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
                headerName: 'ID',
                field: 'id',
                width: 100,
                minWidth: 100,
                maxWidth: 150
            },
            {
                headerName: 'Name',
                field: 'name',
                width: 200,
                minWidth: 200
            }
        ];

        return headers;
    }

    private setupRows() {
        this.brandService.findAll().subscribe(models => {
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
        this.router.navigate(['/pages/manage-brands/brand/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/manage-brands/brand/' + model.id]);
    }

}
