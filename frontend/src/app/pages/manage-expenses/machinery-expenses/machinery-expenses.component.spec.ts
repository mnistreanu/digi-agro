import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MachineryExpensesComponent} from "./machinery-expenses.component";

describe('MachineryExpensesComponent', () => {
    let component: MachineryExpensesComponent;
    let fixture: ComponentFixture<MachineryExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [MachineryExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MachineryExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
