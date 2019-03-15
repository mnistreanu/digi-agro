import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ParcelModel} from '../parcel.model';
import {ParcelService} from '../../../../services/parcel/parcel.service';
import {LangService} from '../../../../services/lang.service';

@Component({
    selector: 'app-parcel-list',
    templateUrl: './parcel-list.component.html',
    styleUrls: ['./parcel-list.component.scss']
})
export class ParcelListComponent implements OnInit {

    @Output() dataChanged: EventEmitter<ParcelModel[]> = new EventEmitter<ParcelModel[]>();
    @Output() centerChanged: EventEmitter<any> = new EventEmitter<any>();

    options: GridOptions;
    context;

    models: ParcelModel[] = [];

    constructor(private parcelService: ParcelService,
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
                headerName: 'info.name',
                field: 'name',
                tooltipField: 'description',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.cadaster-number',
                field: 'cadasterNumber',
                width: 140,
                minWidth: 140
            },
            {
                headerName: 'parcel.land-worthiness-points',
                field: 'landWorthinessPoints',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'parcel.area',
                field: 'area',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'crop.crop',
                field: 'description',
                width: 200,
                minWidth: 200
            }

        ];

        headers.forEach(h => {
            this.langService.get(h.headerName).subscribe(m => h.headerName = m);
        });

        return headers;
    }


    public setupRows() {
        this.parcelService.find().subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.parcelService.adjust(this.models);
            this.dataChanged.emit(this.models);
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
        const firstCoord = model.paths[0];
        this.centerChanged.emit(firstCoord);
    }

}
