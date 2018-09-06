import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FuelExpensesComponent} from "./fuel-expenses.component";

describe('FuelExpensesComponent', () => {
    let component: FuelExpensesComponent;
    let fixture: ComponentFixture<FuelExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [FuelExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FuelExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
