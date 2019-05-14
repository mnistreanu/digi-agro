import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelFertilizerApplicationComponent } from './parcel-fertilizer-application.component';

describe('ParcelFertilizerApplicationComponent', () => {
    let component: ParcelFertilizerApplicationComponent;
    let fixture: ComponentFixture<ParcelFertilizerApplicationComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ParcelFertilizerApplicationComponent ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelFertilizerApplicationComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
