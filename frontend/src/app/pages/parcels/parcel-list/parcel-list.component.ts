import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ParcelService} from '../../../services/parcel.service';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-parcel-list',
    templateUrl: './parcel-list.component.html',
    styleUrls: ['./parcel-list.component.scss']
})
export class ParcelListComponent implements OnInit {

    options: GridOptions;
    context;

    models: ParcelModel[] = [];
    selectedParcelFirstCoord: any;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private parcelService: ParcelService,
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

        const headers: ColDef[] = [
            {
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
            },
            {
                headerName: 'info.name',
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.cadaster-number',
                field: 'cadasterNumber',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.land-worthiness-points',
                field: 'landWorthinessPoints',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.area',
                field: 'area',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'info.description',
                field: 'description',
                width: 200,
                minWidth: 200
            }

        ];

        headers.forEach(h => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }


    public setupRows() {
        this.parcelService.find().subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.parcelService.adjust(this.models);
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

    onSelectionChanged() {
        const model = this.options.api.getSelectedRows()[0];
        this.selectedParcelFirstCoord = model.paths[0];
    }

    public addParcel() {
        this.router.navigate(['./-1'], {relativeTo: this.route});
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }

}
