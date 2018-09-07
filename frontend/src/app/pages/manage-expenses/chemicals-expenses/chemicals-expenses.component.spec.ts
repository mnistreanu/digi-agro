import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ChemicalsExpensesComponent} from "./chemicals-expenses.component";

describe('ChemicalsExpensesComponent', () => {
    let component: ChemicalsExpensesComponent;
    let fixture: ComponentFixture<ChemicalsExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ChemicalsExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ChemicalsExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
