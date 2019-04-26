import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ParcelsMapComponent} from './parcels-map.component';

describe('ParcelsMapComponent', () => {
    let component: ParcelsMapComponent;
    let fixture: ComponentFixture<ParcelsMapComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ParcelsMapComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelsMapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
