import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDiagnosisSummarizerComponent } from './parcel-dianosis-summarizer.component';

describe('ParcelDiagnosisSummarizerComponent', () => {
    let component: ParcelDiagnosisSummarizerComponent;
    let fixture: ComponentFixture<ParcelDiagnosisSummarizerComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ParcelDiagnosisSummarizerComponent ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ParcelDiagnosisSummarizerComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
