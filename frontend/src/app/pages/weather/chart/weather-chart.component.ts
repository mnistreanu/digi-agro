import { Component, OnInit } from '@angular/core';
import * as CanvasJS from 'assets/js/canvasjs/canvasjs.min';
import {AppState} from '../../../app.state';

declare const $: any;

@Component({
  selector: 'app-weather-chart',
  templateUrl: './weather-chart.component.html',
  styleUrls: ['./weather-chart.component.scss']
})
export class WeatherChartComponent implements OnInit {

  private chart: any;

  constructor(private _state: AppState) {
  }

  ngOnInit() {
      this.subscribeToMenuEvents();
      this.setupWeatherChart();
  }

    private subscribeToMenuEvents() {
        this._state.subscribe('menu.isCollapsed', () => {
            setTimeout(() => {
                this.resizeChart(true);
            });
        });
    }


    public setupWeatherChart() {
    this.chart = new CanvasJS.Chart('chartContainer', {
      title: {
        text: 'Weather Forecast'
      },
      axisY: {
        includeZero: false,
        suffix: ' °C',
        maximum: 40,
        gridThickness: 0
      },
      toolTip: {
        shared: true,
        content: '{name} </br> <strong>Temperature: </strong> </br> Min: {y[0]} °C, Max: {y[1]} °C'
      },
      data: [{
        type: 'rangeSplineArea',
        fillOpacity: 0.1,
        color: '#91AAB1',
        indexLabelFormatter: this.chartDataFormatter,
        dataPoints: [
          { label: 'Ianaurie', y: [15, 26], name: 'rainy' },
          { label: 'Februarie', y: [15, 27], name: 'rainy' },
          { label: 'Martie', y: [15, 27], name: 'rainy' },
          { label: 'Aprilie', y: [13, 27], name: 'sunny' },
          { label: 'Mai', y: [14, 27], name: 'sunny' },
          { label: 'Iunie', y: [15, 26], name: 'cloudy' },
          { label: 'Iulie', y: [17, 26], name: 'sunny' },
          { label: 'August', y: [16, 27], name: 'rainy' },
          { label: 'Septembrie', y: [14, 27], name: 'sunny' },
          { label: 'Octombrie', y: [15, 26], name: 'cloudy' },
          { label: 'Noiembrie', y: [17, 26], name: 'sunny' },
          { label: 'Decembrie', y: [16, 27], name: 'rainy' },
        ]
      }]
    });
    this.chart.render();

    this.addChartImages();

    $(window).resize(() => {
      this.resizeChart(false);
    });

  }

  private chartDataFormatter(e) {
    const value = e.dataPoint.y[e.index];
    const isFirstColumn = e.dataPoint.x === 0;
    if (e.index === 0 && isFirstColumn) {
      return ' Min ' + value + '°';
    }
    else if (e.index == 1 && isFirstColumn) {
      return ' Max ' + value + '°';
    }
    else {
      return value + '°';
    }
  }

  private addChartImages() {
    const canvasContainer = $('#chartContainer').find('>.canvasjs-chart-container');
    this.chart.data[0].dataPoints.forEach(dataPoint => {
      const pointName = dataPoint.name;
      const chartImageIdentifier = 'char-image-' + dataPoint.x;
      let imgElement;

      if (pointName == 'cloudy') {
        imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/cloudy.png');
      }
      else if (pointName == 'rainy') {
        imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/rainy.png');
      }
      else if (pointName == 'sunny') {
        imgElement = $('<img>').attr('src', 'https://canvasjs.com/wp-content/uploads/images/gallery/gallery-overview/sunny.png');
      }

      this.adjustChartImagePosition(imgElement, dataPoint);
      imgElement.attr('id', chartImageIdentifier).appendTo(canvasContainer);
    });
  }

  private adjustChartImagePosition(imageElement, dataPoint) {
    const imageCenter = this.chart.axisX[0].convertValueToPixel(dataPoint.x);
    const imageTop = this.chart.axisY[0].convertValueToPixel(this.chart.axisY[0].maximum);

    imageElement.css({
      width: '40px',
      position: 'absolute',
      left: imageCenter - 20 + 'px',
      top: imageTop + 'px'
    });
  }

  private resizeChart(renderRequired) {
    if (renderRequired) {
      this.chart.render();
    }

    this.chart.data[0].dataPoints.forEach(dataPoint => {
      const chartImageIdentifier = 'char-image-' + dataPoint.x;
      const imageCenter = this.chart.axisX[0].convertValueToPixel(dataPoint.x) - 20;
      $('#' + chartImageIdentifier).css({'left': imageCenter});
    });
  }
}
