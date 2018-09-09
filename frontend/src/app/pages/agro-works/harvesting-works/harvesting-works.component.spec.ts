import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HarvestingWorksComponent} from "./harvesting-works.component";

describe('HarvestingWorksComponent', () => {
    let component: HarvestingWorksComponent;
    let fixture: ComponentFixture<HarvestingWorksComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [HarvestingWorksComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HarvestingWorksComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
