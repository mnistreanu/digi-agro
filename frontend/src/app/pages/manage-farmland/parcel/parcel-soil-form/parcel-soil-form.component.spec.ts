import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelSoilFormComponent } from './parcel-soil-form.component';

describe('ParcelSoilFormComponent', () => {
    let component: ParcelSoilFormComponent;
    let fixture: ComponentFixture<ParcelSoilFormComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ParcelSoilFormComponent ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelSoilFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
