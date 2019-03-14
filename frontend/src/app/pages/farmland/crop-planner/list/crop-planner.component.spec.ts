import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CropPlannerComponent} from './parcel-list.component';

describe('CropPlannerComponent', () => {
    let component: CropPlannerComponent;
    let fixture: ComponentFixture<CropPlannerComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CropPlannerComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CropPlannerComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
