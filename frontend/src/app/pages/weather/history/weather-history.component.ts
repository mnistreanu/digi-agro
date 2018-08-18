import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {WeatherService} from '../../../services/weather.service';
import {WeatherHistoryModel} from './weather-history.model';
import {CustomImageRendererComponent} from '../../../modules/aggrid/custom-image-renderer/custom-image-renderer.component';

@Component({
    selector: 'app-weather-history',
    templateUrl: './weather-history.component.html',
    styleUrls: ['./weather-history.component.scss']
})
export class WeatherHistoryComponent implements OnInit {

    options: GridOptions;
    context;

    // models: WeatherModel[] = [];

    labelDate: string;
    labelLocation: string;
    labelTemperature: string;
    labelHumidity: string;
    labelCondition: string;
    labelWind: string;

    constructor(private weatherService: WeatherService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('weather.date').subscribe(msg => this.labelDate = msg);
        this.langService.get('weather.location').subscribe(msg => this.labelLocation = msg);
        this.langService.get('weather.temperature').subscribe(msg => this.labelTemperature = msg);
        this.langService.get('weather.humidity').subscribe(msg => this.labelHumidity = msg);
        this.langService.get('weather.condition').subscribe(msg => this.labelCondition = msg);
        this.langService.get('weather.wind').subscribe(msg => this.labelWind = msg);
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
                headerName: this.labelDate,
                field: 'date',
                width: 80,
                minWidth: 80
            },
            // {
            //     headerName: this.labelLocation,
            //     field: 'location',
            //     width: 200,
            //     minWidth: 200
            // },
            {
                headerName: this.labelTemperature,
                field: 'temperature',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            },
            {
                headerName: this.labelHumidity,
                field: 'humidity',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            },
            {
                headerName: this.labelCondition,
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
                headerName: this.labelWind,
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
            this.options.api.sizeColumnsToFit();
        }, 500);
    }

}
