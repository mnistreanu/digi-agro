import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, ColGroupDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {WeatherService} from '../../../services/weather.service';
import {ImageRendererComponent} from '../../../modules/aggrid/image-renderer/image-renderer.component';
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


    constructor(private weatherService: WeatherService,
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

        const headers: (ColDef | ColGroupDef)[] = [
            {
                headerName: 'crop.name',
                children: [
                    {
                        headerName: '',
                        field: 'icon',
                        cellRendererFramework: ImageRendererComponent,
                        cellRendererParams: {
                            iconField: 'icon'
                        },
                        width: 40,
                        minWidth: 40,
                    },
                    {
                        headerName: 'crop.planting-date',
                        field: 'date',
                        width: 90,
                        minWidth: 90,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'crop.seeds',
                        field: 'cropAndVariety',
                        width: 180,
                        minWidth: 180
                    },
                    {
                        headerName: 'crop.unit-of-measure-short',
                        field: 'unitOfMeasure',
                        width: 60,
                        minWidth: 60,
                        suppressFilter: true,
                        headerTooltip: 'crop.unit-of-measure-long',
                    },
                    {
                        headerName: 'parcel.area',
                        field: 'sownArea',
                        width: 50,
                        minWidth: 50,
                        suppressFilter: true,
                    },
                    {
                        headerName: 'parcel.parcels',
                        field: 'parcels',
                        width: 200,
                        minWidth: 200,
                        suppressFilter: true,
                    }
                ]
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
                headerName: 'expenses.sowing',
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

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }

            header.children.forEach(childHeader => {
                if (childHeader.headerName) {
                    this.langService.get(childHeader.headerName).subscribe(m => childHeader.headerName = m);
                }
            });

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

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
