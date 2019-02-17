import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {WeatherService} from '../../../services/weather.service';
import {WeatherHistoryModel} from './weather-history.model';
import {ImageRendererComponent} from '../../../modules/aggrid/image-renderer/image-renderer.component';
import {AppConfig} from '../../../app.config';
import * as CanvasJS from 'assets/js/canvasjs/canvasjs.min';


@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'app-weather-history',
    templateUrl: './weather-history.component.html',
    styleUrls: ['./weather-history.component.scss']
})
export class WeatherHistoryComponent implements OnInit {

    public config: any;
    public configFn: any;
    options: GridOptions;
    context;

    // models: WeatherModel[] = [];

    labelDate: string;
    labelLocation: string;
    labelTemperature: string;
    labelHumidity: string;
    labelCondition: string;
    labelWind: string;
    labelPressure: string;

    public lineChartType = 'line';
    public lineChartLegend = true;
    public lineChartLabels: string[];
    public lineChartData: Array<any>;
    public lineChartColors: any[];
    public lineChartOptions: any;

    constructor(private _appConfig: AppConfig,
                private weatherService: WeatherService,
                private langService: LangService) {
        this.config = this._appConfig.config;
        this.configFn = this._appConfig;
    }

    ngOnInit() {
        // todo: remove. This is only for testing
        console.log('CanvasJS initialized: ', CanvasJS);

        this.setupLabels();
        this.setupGrid();
        let chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: "Basic Column Chart in Angular"
            },
            data: [{
                type: "column",
                dataPoints: [
                    { y: 71, label: "Apple" },
                    { y: 55, label: "Mango" },
                    { y: 50, label: "Orange" },
                    { y: 65, label: "Banana" },
                    { y: 95, label: "Pineapple" },
                    { y: 68, label: "Pears" },
                    { y: 28, label: "Grapes" },
                    { y: 34, label: "Lychee" },
                    { y: 14, label: "Jackfruit" }
                ]
            }]
        });

        chart.render();
    }

    private setupLabels() {
        this.langService.get('weather.date').subscribe(msg => this.labelDate = msg);
        this.langService.get('weather.location').subscribe(msg => this.labelLocation = msg);
        this.langService.get('weather.temperature').subscribe(msg => this.labelTemperature = msg);
        this.langService.get('weather.humidity').subscribe(msg => this.labelHumidity = msg);
        this.langService.get('weather.condition').subscribe(msg => this.labelCondition = msg);
        this.langService.get('weather.wind').subscribe(msg => this.labelWind = msg);
        this.langService.get('weather.pressure').subscribe(msg => this.labelPressure = msg);
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
            {
                headerName: this.labelLocation,
                field: 'location',
                width: 200,
                minWidth: 50
            },
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
                cellRendererFramework: ImageRendererComponent,
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
            },
            {
                headerName: this.labelPressure,
                field: 'pressure',
                width: 100,
                minWidth: 100,
                suppressFilter: true,
                suppressSorting : true,
            }

        ];

        return headers;
    }


    public setupRows() {
        const dateTo = new Date();
        let dateFrom = new Date();
        dateFrom.setHours(dateTo.getHours() - 24*15);
        this.weatherService.findWeatherHistory('MD', 'HN', dateFrom, dateTo).subscribe(payloadModel => {
            debugger;
            if (payloadModel.status == 'success') {
                const rows = payloadModel.payload.map(data => {
                    const model = new WeatherHistoryModel();
                    model.date = new Date(data.dt).toLocaleDateString();
                    model.location = data.countyId;
                    model.icon = '/assets/img/notifications/weather-rain-alert.png';
                    model.temperature = data.tempMax + '\u00B0C / ' + data.tempMin + '\u00B0C';
                    model.condition = data.main + ', ' + data.description;
                    model.humidity = 'air: ' + data.humidityAir + ' %' + ', soil: ' + data.humiditySoil + '%';
                    model.pressure = data.pressure;
                    model.wind = data.speed + ' km/h NW';
                    return model;
                });
                this.options.api.setRowData(rows);
            } else if (payloadModel.status == 'warning') {
                this.options.api.setRowData(new Array());
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

    public setupChart() {
        // --- Line Chart ---
        this.lineChartLabels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
        this.lineChartData = [
            {data: [2, 5, 8, 18, 26, 35, 40], label: 'Temp Max'},
            {data: [-8, -8, -4, 1, 8, 17, 19], label: 'Temp Min'},
            {data: [-2, 4, 7, 9, 10, 27, 20], label: 'Temp Avg'}
        ];
        this.lineChartColors = [
            {
                borderWidth: 2,
                backgroundColor: this.configFn.rgba(this.config.colors.success, 0.5),
                borderColor: this.config.colors.success,
                pointBorderColor: this.config.colors.default,
                pointHoverBorderColor: this.config.colors.success,
                pointHoverBackgroundColor: this.config.colors.default,
                hoverBackgroundColor: this.config.colors.success
            },
            {
                borderWidth: 2,
                backgroundColor: this.configFn.rgba(this.config.colors.warning, 0.5),
                borderColor: this.config.colors.warning,
                pointBorderColor: this.config.colors.default,
                pointHoverBorderColor: this.config.colors.warning,
                pointHoverBackgroundColor: this.config.colors.default,
                hoverBackgroundColor: this.config.colors.warning
            },
            {
                borderWidth: 2,
                backgroundColor: this.configFn.rgba(this.config.colors.primary, 0.5),
                borderColor: this.config.colors.primary,
                pointBorderColor: this.config.colors.default,
                pointHoverBorderColor: this.config.colors.primary,
                pointHoverBackgroundColor: this.config.colors.default,
                hoverBackgroundColor: this.config.colors.primary
            }
        ];
        this.lineChartOptions = {
            scales: {
                yAxes: [{
                    ticks: {
                        fontColor: this.configFn.rgba(this.config.colors.gray, 0.7),
                        beginAtZero: true
                    },
                    gridLines: {
                        display: true,
                        zeroLineColor: this.configFn.rgba(this.config.colors.gray, 0.5),
                        zeroLineWidth: 1,
                        color: this.configFn.rgba(this.config.colors.gray, 0.1)
                    }
                }],
                xAxes: [{
                    ticks: {
                        fontColor: this.configFn.rgba(this.config.colors.gray, 0.7)
                    },
                    gridLines: {
                        display: true,
                        zeroLineColor: this.configFn.rgba(this.config.colors.gray, 0.5),
                        zeroLineWidth: 1,
                        color: this.configFn.rgba(this.config.colors.gray, 0.1)
                    }
                }]
            },
            legend: {
                labels: {
                    fontColor: this.configFn.rgba(this.config.colors.gray, 0.9),
                }
            },
            tooltips: {
                enabled: true,
                backgroundColor: this.configFn.rgba(this.config.colors.main, 0.7)
            }
        };
    }

    public chartClicked(e: any): void {
        // console.log(e);
    }

    public chartHovered(e: any): void {
        // console.log(e);
    }

//     public setupWeatherChart() {
//
//     var chart = new CanvasJS.Chart("chartContainer", {
//         title:{
//             text: "Weekly Weather Forecast"
//         },
//         axisY: {
//             includeZero: false,
//             suffix: " °C",
//             maximum: 40,
//             gridThickness: 0
//         },
//         toolTip:{
//             shared: true,
//             content: "{name} </br> <strong>Temperature: </strong> </br> Min: {y[0]} °C, Max: {y[1]} °C"
//         },
//         data: [{
//             type: "rangeSplineArea",
//             fillOpacity: 0.1,
//             color: "#91AAB1",
//             indexLabelFormatter: formatter,
//             dataPoints: [
//                 { label: "Monday", y: [15, 26], name: "rainy" },
//                 { label: "Tuesday", y: [15, 27], name: "rainy" },
//                 { label: "Wednesday", y: [13, 27], name: "sunny" },
//                 { label: "Thursday", y: [14, 27], name: "sunny" },
//                 { label: "Friday", y: [15, 26], name: "cloudy" },
//                 { label: "Saturday", y: [17, 26], name: "sunny" },
//                 { label: "Sunday", y: [16, 27], name: "rainy" }
//             ]
//         }]
//     });
//     chart.render();
//
//     var images = [];
//
//     addImages(chart);
//
//     function addImages(chart) {
//         for(var i = 0; i < chart.data[0].dataPoints.length; i++){
//             var dpsName = chart.data[0].dataPoints[i].name;
//             if(dpsName == "cloudy"){
//                 images.push($("<img>").attr("src", "https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/cloudy.png"));
//             } else if(dpsName == "rainy"){
//                 images.push($("<img>").attr("src", "https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/rainy.png"));
//             } else if(dpsName == "sunny"){
//                 images.push($("<img>").attr("src", "https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/sunny.png"));
//             }
//
//             images[i].attr("class", dpsName).appendTo($("#chartContainer>.canvasjs-chart-container"));
//             positionImage(images[i], i);
//         }
//     }
//
//     function positionImage(image, index) {
//         var imageCenter = chart.axisX[0].convertValueToPixel(chart.data[0].dataPoints[index].x);
//         var imageTop =  chart.axisY[0].convertValueToPixel(chart.axisY[0].maximum);
//
//         image.width("40px")
//             .css({ "left": imageCenter - 20 + "px",
//                 "position": "absolute","top":imageTop + "px",
//                 "position": "absolute"});
//     }
//
//     $( window ).resize(function() {
//         var cloudyCounter = 0, rainyCounter = 0, sunnyCounter = 0;
//         var imageCenter = 0;
//         for(var i=0;i<chart.data[0].dataPoints.length;i++) {
//             imageCenter = chart.axisX[0].convertValueToPixel(chart.data[0].dataPoints[i].x) - 20;
//             if(chart.data[0].dataPoints[i].name == "cloudy") {
//                 $(".cloudy").eq(cloudyCounter++).css({ "left": imageCenter});
//             } else if(chart.data[0].dataPoints[i].name == "rainy") {
//                 $(".rainy").eq(rainyCounter++).css({ "left": imageCenter});
//             } else if(chart.data[0].dataPoints[i].name == "sunny") {
//                 $(".sunny").eq(sunnyCounter++).css({ "left": imageCenter});
//             }
//         }
//     });
//
//     function formatter(e) {
//         if(e.index === 0 && e.dataPoint.x === 0) {
//             return " Min " + e.dataPoint.y[e.index] + "°";
//         } else if(e.index == 1 && e.dataPoint.x === 0) {
//             return " Max " + e.dataPoint.y[e.index] + "°";
//         } else{
//             return e.dataPoint.y[e.index] + "°";
//         }
//     }
//
// }

}
