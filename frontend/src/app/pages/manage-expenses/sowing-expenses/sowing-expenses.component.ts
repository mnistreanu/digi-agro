import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from "../../../services/lang.service";
import {WeatherService} from "../../../services/weather.service";
import {CustomImageRendererComponent} from "../../../modules/aggrid/custom-image-renderer/custom-image-renderer.component";
import {WeatherHistoryModel} from "../../weather/history/weather-history.model";

@Component({
    selector: 'app-sowing-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './sowing-expenses.component.html',
    styleUrls: ['./sowing-expenses.component.scss']
})
export class SowingExpensesComponent implements OnInit {
    options: GridOptions;
    context;

    labelDate: string;
    labelCropIcon: string;
    labelCrop: string;
    labelCropVariety: string;
    labelUnitOfMeasure: string;
    labelArea: string;
    labelParcels: string;
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
        this.langService.get('weather.date').subscribe(msg => this.labelDate = msg);
        this.langService.get('crop.name').subscribe(msg => this.labelCropIcon = msg);
        this.langService.get('crop.name').subscribe(msg => this.labelCrop = msg);
        this.langService.get('weather.temperature').subscribe(msg => this.labelCropVariety = msg);
        this.langService.get('weather.humidity').subscribe(msg => this.labelUnitOfMeasure = msg);
        this.langService.get('weather.wind').subscribe(msg => this.labelArea = msg);
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

        this.options.defaultColDef = {
            cellStyle: () => {
                return {
                    paddingLeft: '5px',
                    paddingRight: '5px'
                };
            }
        };

        this.setupRows();
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: this.labelDate,
                field: 'date',
                width: 100,
                minWidth: 100,
            },
            {
                headerName: this.labelCrop,
                field: 'condition',
                cellRendererFramework: CustomImageRendererComponent,
                cellRendererParams: {
                    textField: 'condition',
                    iconField: 'icon'
                },
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelCropVariety,
                field: 'temperature',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            },
            {
                headerName: this.labelUnitOfMeasure,
                field: 'humidity',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            },
            {
                headerName: this.labelCropIcon,
                field: 'condition',
                cellRendererFramework: CustomImageRendererComponent,
                cellRendererParams: {
                    textField: 'condition',
                    iconField: 'icon'
                },
                width: 200,
                minWidth: 200,
            },
            {
                headerName: this.labelArea,
                field: 'wind',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            }

        ];

        return headers;
    }


    public setupRows() {
        this.weatherService.findWeatherHistory().subscribe(payloadModel => {
            const rows = payloadModel.payload.map(data => {
                const model = new WeatherHistoryModel();
                model.date = new Date(data.dt).toLocaleDateString();
                model.icon = '/assets/img/notifications/weather-rain-alert.png';
                model.temperature = data.tempMax + '\u00B0C / ' + data.tempMin + '\u00B0C';
                model.condition = data.main; //TODO de tradus din resurse, de exemplu: clouds, rain
                model.location = 'Nisporeni';
                model.humidity = data.humidity + ' %';
                model.wind = data.windSpeed + ' km/h NW';
                return model;
            });
            this.options.api.setRowData(rows);
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
}
