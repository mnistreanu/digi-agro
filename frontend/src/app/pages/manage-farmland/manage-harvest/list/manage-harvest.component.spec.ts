import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManageHarvestComponent} from './parcel-list.component';

describe('ManageHarvestComponent', () => {
    let component: ManageHarvestComponent;
    let fixture: ComponentFixture<ManageHarvestComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ManageHarvestComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ManageHarvestComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
