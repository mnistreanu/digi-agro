import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BriefParcelMapComponent} from './brief-parcel-map.component';

describe('BriefParcelMapComponent', () => {
    let component: BriefParcelMapComponent;
    let fixture: ComponentFixture<BriefParcelMapComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [BriefParcelMapComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(BriefParcelMapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
