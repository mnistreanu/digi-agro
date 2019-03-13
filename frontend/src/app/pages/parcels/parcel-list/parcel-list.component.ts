import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ParcelService} from '../../../services/parcel.service';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ActivatedRoute, Router} from '@angular/router';
import {NumericUtil} from '../../../common/numericUtil';
import {PinnedRowRendererComponent} from '../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';

@Component({
    selector: 'app-parcel-list',
    templateUrl: './parcel-list.component.html',
    styleUrls: ['./parcel-list.component.scss']
})
export class ParcelListComponent implements OnInit {

    options: GridOptions;
    context;

    models: ParcelModel[] = [];
    center: any;

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
                width: 150,
                minWidth: 100
            },
            {
                headerName: 'parcel.area',
                field: 'area',
                width: 150,
                minWidth: 100,
                valueFormatter: (params) => NumericUtil.format(params.value)
            },
            {
                headerName: 'parcel.irrigated-short',
                field: 'irrigated',
                width: 80,
                minWidth: 80,
                valueFormatter: (params) => NumericUtil.formatBoolean(params.value)
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
            this.setupSummaryRow(this.models);
        });
    }

    private setupSummaryRow(models) {
        const summaryRow = new ParcelModel();
        models.forEach(model => this.aggregate(summaryRow, model, true));
        this.options.api.setPinnedBottomRowData([summaryRow]);
    }

    private aggregate(source: ParcelModel, item: ParcelModel, applyAddition) {
        const sumFields = ['area'];
        sumFields.forEach(field => {
            source[field] = source[field] || 0;
            if (applyAddition) {
                source[field] += item[field] || 0;
            }
            else {
                source[field] -= item[field] || 0;
            }
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
        this.center = model.center;
    }

    public addParcel() {
        this.router.navigate(['./-1'], {relativeTo: this.route});
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }

}
