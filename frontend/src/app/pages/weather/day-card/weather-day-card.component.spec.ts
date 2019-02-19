import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WeatherDayCardComponent} from './weather-day-card.component';

describe('WeatherDayCardComponent', () => {
    let component: WeatherDayCardComponent;
    let fixture: ComponentFixture<WeatherDayCardComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [WeatherDayCardComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(WeatherDayCardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
