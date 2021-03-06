import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {WeatherService} from '../../../services/weather.service';
import {AppConfig} from '../../../app.config';
import * as CanvasJS from 'assets/js/canvasjs/canvasjs.min';
import {DateUtil} from '../../../common/dateUtil';
import {AppState} from '../../../app.state';
import {WeatherModel} from '../weather.model';


declare const $: any;


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

    private chart: any;

    forecastModels: WeatherModel[] = [];
    historyModels: WeatherModel[];

    public lineChartType = 'line';
    public lineChartLegend = true;
    public lineChartLabels: string[];
    public lineChartData: Array<any>;
    public lineChartColors: any[];
    public lineChartOptions: any;

    constructor(private _appConfig: AppConfig,
                private _state: AppState,
                private weatherService: WeatherService,
                private langService: LangService) {
        this.config = this._appConfig.config;
        this.configFn = this._appConfig;
    }

    ngOnInit() {
        this.loadWeatherForecast();
        this.loadWeatherHistory();
    }

    public loadWeatherForecast() {
        const dateTo = new Date();
        const dateFrom = new Date();
        dateFrom.setHours(dateTo.getHours() - 24 * 25);
        this.weatherService.findWeatherHistory('MD', 'HN', dateFrom, dateTo).subscribe(payloadModel => {
            if (payloadModel.status == 'success') {
                payloadModel.payload.forEach((data, index) => {
                    if (index >= 6) {
                        // server must return only 6 models
                        return;
                    }
                    const model = new WeatherModel();
                    model.owmId = data.weatherId;
                    model.date = DateUtil.formatLocalizedDay(data.dt);
                    model.location = data.countyId;
                    model.main = data.main;
                    this.langService.get('owm-code.' + data.weatherId).subscribe(msg => model.description = msg);
                    model.icon = '/assets/img/openweather/' + data.icon + '.png';
                    model.temperature = data.tempMax + '\u00B0C / ' + data.tempMin + '\u00B0C';
                    model.tempMax = data.tempMax;
                    model.tempMin = data.tempMin;
                    model.humidityAir = data.humidityAir;
                    model.humiditySoil = data.humiditySoil;
                    model.pressure = data.pressure;
                    model.wind = data.speed;
                    this.forecastModels.push(model);
                });
            }
        });
    }

    public loadWeatherHistory() {
        const dateTo = new Date();
        const dateFrom = new Date();
        dateFrom.setHours(dateTo.getHours() - 24 * 25);
        this.weatherService.findWeatherHistory('MD', 'HN', dateFrom, dateTo).subscribe(payloadModel => {
            if (payloadModel.status == 'success') {
                this.historyModels = payloadModel.payload.map(data => {
                    const model = new WeatherModel();
                    model.owmId = data.weatherId;
                    model.date = DateUtil.formatLocalizedDay(data.dt);
                    model.location = data.countyId;
                    model.main = data.main;
                    this.langService.get('owm-code.' + data.weatherId).subscribe(msg => model.description = msg);
                    model.icon = '/assets/img/openweather/' + data.icon + '.png';
                    model.temperature = data.tempMax + '\u00B0C / ' + data.tempMin + '\u00B0C';
                    model.tempMax = data.tempMax;
                    model.tempMin = data.tempMin;
                    model.humidityAir = data.humidityAir;
                    model.humiditySoil = data.humiditySoil;
                    model.pressure = data.pressure;
                    model.wind = data.speed;
                    return model;
                });
            }
        });
    }
    //
    // public setupWeatherChart() {
    //     this.chart = new CanvasJS.Chart('chartContainer', {
    //         title: {
    //             text: 'Weather Forecast'
    //         },
    //         axisY: {
    //             includeZero: false,
    //             suffix: ' °C',
    //             maximum: 40,
    //             gridThickness: 0
    //         },
    //         toolTip: {
    //             shared: true,
    //             content: '{name} </br> <strong>Temperature: </strong> </br> Min: {y[0]} °C, Max: {y[1]} °C'
    //         },
    //         data: [{
    //             type: 'rangeSplineArea',
    //             fillOpacity: 0.1,
    //             color: '#91AAB1',
    //             indexLabelFormatter: this.chartDataFormatter,
    //             dataPoints: [
    //                 { label: 'Ianaurie', y: [15, 26], name: 'rainy' },
    //                 { label: 'Februarie', y: [15, 27], name: 'rainy' },
    //                 { label: 'Martie', y: [15, 27], name: 'rainy' },
    //                 { label: 'Aprilie', y: [13, 27], name: 'sunny' },
    //                 { label: 'Mai', y: [14, 27], name: 'sunny' },
    //                 { label: 'Iunie', y: [15, 26], name: 'cloudy' },
    //                 { label: 'Iulie', y: [17, 26], name: 'sunny' },
    //                 { label: 'August', y: [16, 27], name: 'rainy' },
    //                 { label: 'Septembrie', y: [14, 27], name: 'sunny' },
    //                 { label: 'Octombrie', y: [15, 26], name: 'cloudy' },
    //                 { label: 'Noiembrie', y: [17, 26], name: 'sunny' },
    //                 { label: 'Decembrie', y: [16, 27], name: 'rainy' },
    //             ]
    //         }]
    //     });
    //     this.chart.render();
    //
    //     this.addChartImages();
    //
    //     $(window).resize(() => {
    //         this.resizeChart(false);
    //     });
    //
    // }
    //
    // private chartDataFormatter(e) {
    //     const value = e.dataPoint.y[e.index];
    //     const isFirstColumn = e.dataPoint.x === 0;
    //     if (e.index === 0 && isFirstColumn) {
    //         return ' Min ' + value + '°';
    //     }
    //     else if (e.index == 1 && isFirstColumn) {
    //         return ' Max ' + value + '°';
    //     }
    //     else {
    //         return value + '°';
    //     }
    // }
    //
    // private addChartImages() {
    //     const canvasContainer = $('#chartContainer').find('>.canvasjs-chart-container');
    //     this.chart.data[0].dataPoints.forEach(dataPoint => {
    //         const pointName = dataPoint.name;
    //         const chartImageIdentifier = 'char-image-' + dataPoint.x;
    //         let imgElement;
    //
    //         if (pointName == 'cloudy') {
    //             imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/cloudy.png');
    //         }
    //         else if (pointName == 'rainy') {
    //             imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/rainy.png');
    //         }
    //         else if (pointName == 'sunny') {
    //             imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/sunny.png');
    //         }
    //
    //         this.adjustChartImagePosition(imgElement, dataPoint);
    //         imgElement.attr('id', chartImageIdentifier).appendTo(canvasContainer);
    //     });
    // }
    //
    // private adjustChartImagePosition(imageElement, dataPoint) {
    //     const imageCenter = this.chart.axisX[0].convertValueToPixel(dataPoint.x);
    //     const imageTop = this.chart.axisY[0].convertValueToPixel(this.chart.axisY[0].maximum);
    //
    //     imageElement.css({
    //         width: '40px',
    //         position: 'absolute',
    //         left: imageCenter - 20 + 'px',
    //         top: imageTop + 'px'
    //     });
    // }
    //
    // private resizeChart(renderRequired) {
    //     if (renderRequired) {
    //         this.chart.render();
    //     }
    //
    //     this.chart.data[0].dataPoints.forEach(dataPoint => {
    //         const chartImageIdentifier = 'char-image-' + dataPoint.x;
    //         const imageCenter = this.chart.axisX[0].convertValueToPixel(dataPoint.x) - 20;
    //         $('#' + chartImageIdentifier).css({'left': imageCenter});
    //     });
    // }
}
