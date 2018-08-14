import {Component, OnInit} from '@angular/core';
import {TenantService} from '../../../services/tenant.service';
import {Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {LangService} from "../../../services/lang.service";

@Component({
    selector: 'app-tenant-list',
    templateUrl: './tenant-list.component.html',
    styleUrls: ['./tenant-list.component.scss']
})
export class TenantListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private langService: LangService,
                private tenantService: TenantService) {
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
                headerName: 'info.name',
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'info.description',
                field: 'description',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'tenant.fiscal-code',
                field: 'fiscalCode',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'geo.country',
                field: 'country',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'geo.county',
                field: 'county',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'geo.village-city',
                field: 'city',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'geo.address',
                field: 'address',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'contact.phone',
                field: 'phones',
                width: 200,
                minWidth: 200
            }
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.tenantService.find().subscribe(models => {
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
        this.router.navigate(['/pages/manage-tenants/tenant/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/manage-tenants/tenant/' + model.id]);
    }

}
