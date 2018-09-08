import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PesticidesExpensesComponent} from "./pesticides-expenses.component";

describe('PesticidesExpensesComponent', () => {
    let component: PesticidesExpensesComponent;
    let fixture: ComponentFixture<PesticidesExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [PesticidesExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(PesticidesExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
