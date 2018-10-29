import {Component, OnInit} from '@angular/core';
import {TenantService} from '../../../services/tenant.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {LangService} from '../../../services/lang.service';
import { CropVarietyService } from '../../../services/crop/crop-variety.service';

@Component({
    selector: 'app-crop-varieties-list',
    templateUrl: './crop-variety-list.component.html',
    styleUrls: ['./crop-variety-list.component.scss']
})
export class CropVarietyListComponent implements OnInit {

    options: GridOptions;
    context;

    firstRow: number;
    noPerPage: number;
    totalRecords = 0;
    pageNo = 0;
    pageSize = 10;
    filterMap: Map<string, string> = new Map<string, string>();


    constructor(private router: Router,
                private route: ActivatedRoute,
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
                headerName: 'crop.crop',
                field: 'crop.nameRo',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop.variety',
                field: 'nameRo',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'info.description',
                field: 'descriptionRo',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'crop.seed-consumption-ha',
                field: 'seedConsumptionHa',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'unit-of-measure.unit-short',
                field: 'unitOfMeasure',
                width: 100,
                minWidth: 100
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
            this.totalRecords = data['total_count'];
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

    onPageSizeChanged() {
        const value = Number(document.getElementById('page-size')['value']);
        this.options.paginationPageSize = value;
        this.pageSize = value;
        this.setupRows();
      }

}
