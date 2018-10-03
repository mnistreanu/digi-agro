import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WorksExpensesComponent} from "./works-expenses-list.component";

describe('OtherWorksComponent', () => {
    let component: WorksExpensesComponent;
    let fixture: ComponentFixture<WorksExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [WorksExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(WorksExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
