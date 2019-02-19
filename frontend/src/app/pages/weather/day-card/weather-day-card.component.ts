import {Component, Input, OnInit} from '@angular/core';
import {WeatherModel} from '../weather.model';

@Component({
    selector: 'app-weather-day-card',
    templateUrl: './weather-day-card.component.html',
    styleUrls: ['./weather-day-card.component.scss']
})
export class WeatherDayCardComponent implements OnInit {

    @Input() weather: WeatherModel;

    ngOnInit() {

    }
}
