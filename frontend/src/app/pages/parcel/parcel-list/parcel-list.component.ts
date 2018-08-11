import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ParcelService} from '../../../services/parcel.service';

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

    labelName: string;
    labelCadasterNumber: string;
    labelLandWorthinessPoints: string;
    labelArea: string;
    labelDescription: string;

    constructor(private parcelService: ParcelService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('parcel.name').subscribe(msg => this.labelName = msg);
        this.langService.get('parcel.cadaster-number').subscribe(msg => this.labelCadasterNumber = msg);
        this.langService.get('parcel.land-worthiness-points').subscribe(msg => this.labelLandWorthinessPoints = msg);
        this.langService.get('parcel.area').subscribe(msg => this.labelArea = msg);
        this.langService.get('parcel.description').subscribe(msg => this.labelDescription = msg);
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
                headerName: this.labelName,
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelCadasterNumber,
                field: 'cadasterNumber',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelLandWorthinessPoints,
                field: 'landWorthinessPoints',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelArea,
                field: 'area',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelDescription,
                field: 'description',
                width: 200,
                minWidth: 200
            }

        ];

        return headers;
    }


    public setupRows() {
        this.parcelService.find().subscribe(models => {
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();

            this.models.forEach((parcel) => {
                parcel.fillColor = this.randomColor();
                parcel.paths = [];
                parcel.coordinates.forEach((c) => {
                    parcel.paths.push({
                        lat: c[0],
                        lng: c[1]
                    });
                });
            });


            this.dataChanged.emit(this.models);
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

    private randomColor(): string {
        return '#' + Math.random().toString(16).slice(-3);
    }

    private onSelectionChanged(event) {
        const model = this.options.api.getSelectedRows()[0];
        const firstCoord = model.paths[0];
        this.centerChanged.emit(firstCoord);
    }

}
