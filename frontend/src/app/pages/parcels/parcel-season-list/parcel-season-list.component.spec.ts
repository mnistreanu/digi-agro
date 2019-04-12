import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ParcelSeasonListComponent} from './parcel-season-list.component';

describe('ParcelSeasonListComponent', () => {
    let component: ParcelSeasonListComponent;
    let fixture: ComponentFixture<ParcelSeasonListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ParcelSeasonListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelSeasonListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
