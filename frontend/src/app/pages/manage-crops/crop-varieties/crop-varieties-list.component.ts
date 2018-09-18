import {Component, OnInit} from '@angular/core';
import {TenantService} from '../../../services/tenant.service';
import {Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {LangService} from "../../../services/lang.service";
import { CropVarietyService } from '../../../services/crop/crop-variety.service';

@Component({
    selector: 'app-crop-varieties-list',
    templateUrl: './crop-varieties-list.component.html',
    styleUrls: ['./crop-varieties-list.component.scss']
})
export class CropVarietiesListComponent implements OnInit {

    options: GridOptions;
    context;

    firstRow: number;
    noPerPage: number;   
    totalRecords: number = 0;
    pageNo: number = 0;
    pageSize: number = 10;
    filterMap : Map<string, string> = new Map<string, string>();


    constructor(private router: Router,
                private langService: LangService,
                private cropVarietyService: CropVarietyService) {
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
        this.options.pagination = true;
        this.options.paginationPageSize = this.pageSize;
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
                headerName: 'crop-variety.crop',
                field: 'cropId',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.name-ro',
                field: 'nameRo',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.name-ru',
                field: 'nameRu',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.description-ro',
                field: 'descriptionRo',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.description-ru',
                field: 'descriptionRu',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.seed-consumption-ha',
                field: 'seedConsumptionHa',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop-variety.unit-of-measure',
                field: 'unitOfMeasure',
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
        this.cropVarietyService.findAll(this.pageNo, this.pageSize, this.filterMap, null).subscribe(data => {
            this.options.api.setRowData(JSON.parse(data['items']));
            this.totalRecords = data['total_count']
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
        this.router.navigate(['/pages/manage-crops/crop-varieties/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/manage-crops/crop-varieties/' + model.id]);
    }

    onPageSizeChanged() {
        const value = Number(document.getElementById("page-size")['value']);
        this.options.paginationPageSize = value;
        this.pageSize = value;
        this.setupRows();
      }

}
