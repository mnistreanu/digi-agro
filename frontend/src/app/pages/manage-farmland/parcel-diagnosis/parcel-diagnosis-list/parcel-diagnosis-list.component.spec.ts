import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ParcelDiagnosisListComponent} from './parcel-season-list.component';

describe('ParcelDiagnosisListComponent', () => {
    let component: ParcelDiagnosisListComponent;
    let fixture: ComponentFixture<ParcelDiagnosisListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ParcelDiagnosisListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelDiagnosisListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
