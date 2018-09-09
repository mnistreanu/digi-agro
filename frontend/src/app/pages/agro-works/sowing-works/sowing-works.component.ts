import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, ColGroupDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {WeatherService} from '../../../services/weather.service';
import {CustomImageRendererComponent} from '../../../modules/aggrid/custom-image-renderer/custom-image-renderer.component';
import {SowingWorksModel} from './sowing-works.model';

@Component({
    selector: 'app-sowing-works',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './sowing-works.component.html',
    styleUrls: ['./sowing-works.component.scss']
})
export class SowingWorksComponent implements OnInit {
    options: GridOptions;
    context;

    labelDate: string;
    labelCrop: string;
    labelCropVariety: string;
    labelSeeds: string;
    labelUnitOfMeasureShort: string;
    labelUnitOfMeasureLong: string;
    labelArea: string;
    labelParcels: string;
    labelWorks: string;
    labelConsumed1Ha: string;
    labelConsumedTotal: string;
    labelPriceUnit: string;
    labelCostTotal: string;

    constructor(private weatherService: WeatherService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('crop.planting-date').subscribe(msg => this.labelDate = msg);
        this.langService.get('crop.name').subscribe(msg => this.labelCrop = msg);
        this.langService.get('crop.variety').subscribe(msg => this.labelCropVariety = msg);
        this.langService.get('crop.seeds').subscribe(msg => this.labelSeeds = msg);
        this.langService.get('crop.unit-of-measure-short').subscribe(msg => this.labelUnitOfMeasureShort = msg);
        this.langService.get('crop.unit-of-measure-long').subscribe(msg => this.labelUnitOfMeasureLong = msg);
        this.langService.get('parcel.area').subscribe(msg => this.labelArea = msg);
        this.langService.get('parcel.parcels').subscribe(msg => this.labelParcels = msg);
        this.langService.get('works.sowing').subscribe(msg => this.labelWorks = msg);
        this.langService.get('crop.labelConsumed1Ha').subscribe(msg => this.labelConsumed1Ha = msg);
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

        const headers: (ColDef | ColGroupDef)[] = [
            {
                headerName: '',
                field: 'icon',
                cellRendererFramework: CustomImageRendererComponent,
                cellRendererParams: {
                    iconField: 'icon'
                },
                width: 40,
                minWidth: 40,
            },
            {
                headerName: this.labelDate,
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
            },
            {
                headerName: this.labelSeeds,
                field: 'cropAndVariety',
                width: 180,
                minWidth: 180
            },
            {
                headerName: this.labelUnitOfMeasureShort,
                field: 'unitOfMeasure',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
                headerTooltip: this.labelUnitOfMeasureLong,
            },
            {
                headerName: this.labelArea,
                field: 'sownArea',
                width: 50,
                minWidth: 50,
                suppressFilter: true,
            },
            {
                headerName: this.labelParcels,
                field: 'parcels',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'Norma de insamintare',
                children: [
                    {
                        headerName: '1 Ha',
                        field: 'sown1Ha',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'Suma Ha',
                        field: 'totalSown',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    },

                ]
            },
            {
                headerName: this.labelWorks,
                children: [
                    {
                        headerName: '1 Ha',
                        field: 'sown1Ha',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'Suma Ha',
                        field: 'totalSown',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'Pret',
                        field: 'unitPrice',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'Suma',
                        field: 'totalAmount',
                        width: 100,
                        minWidth: 100,
                        suppressFilter: true,
                    }
                ]
            },
            {
                headerName: 'Registered',
                children: [
                    {
                        headerName: 'By',
                        field: '',
                        width: 100,
                        minWidth: 100,
                    },
                    {
                        headerName: 'At',
                        field: '',
                        width: 60,
                        minWidth: 60,
                    },
                ]
            },
        ];

        return headers;
    }


    public setupRows() {
        // this.weatherService.findWeatherHistory().subscribe(payloadModel => {
        //     const rows = payloadModel.payload.map(data => {
        //         const model = new SowingWorksModel();
        //         model.date = new Date(data.dt).toLocaleDateString();
        //         model.icon = '/assets/img/crops/wheat.png';
        //         model.crop = 'Porumb';
        //         model.variety = 'Mama';
        //         model.cropAndVariety = 'Porumb "Mama"',
        //         model.unitOfMeasure = 'tone';
        //         model.sownArea = 121;
        //         model.parcels = 'Jora de Sus, Campul din deal, Delta Dunarii',
        //         model.sown1Ha = 51;
        //         model.totalSown = model.sownArea * model.sown1Ha;
        //         model.unitPrice = 15.20;
        //         model.totalAmount = model.unitPrice * model.unitPrice;
        //         return model;
        //     });
        //     this.options.api.setRowData(rows);
        // });
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
}
