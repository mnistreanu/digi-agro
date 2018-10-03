import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WorksExpensesListComponent} from "./works-expenses-list.component";

describe('OtherWorksComponent', () => {
    let component: WorksExpensesListComponent;
    let fixture: ComponentFixture<WorksExpensesListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [WorksExpensesListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(WorksExpensesListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
