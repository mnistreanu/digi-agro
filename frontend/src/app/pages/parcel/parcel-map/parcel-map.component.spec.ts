import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ParcelMapComponent} from './parcel-map.component';

describe('ParcelMapComponent', () => {
    let component: ParcelMapComponent;
    let fixture: ComponentFixture<ParcelMapComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ParcelMapComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelMapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
