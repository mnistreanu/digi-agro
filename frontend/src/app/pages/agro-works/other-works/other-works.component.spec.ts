import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OtherWorksComponent} from "./other-works.component";

describe('OtherWorksComponent', () => {
    let component: OtherWorksComponent;
    let fixture: ComponentFixture<OtherWorksComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [OtherWorksComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(OtherWorksComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
