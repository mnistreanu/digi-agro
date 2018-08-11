import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TelemetryMapComponent} from './telemetry-map.component';

describe('TelemetryMapComponent', () => {
    let component: TelemetryMapComponent;
    let fixture: ComponentFixture<TelemetryMapComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [TelemetryMapComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(TelemetryMapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
