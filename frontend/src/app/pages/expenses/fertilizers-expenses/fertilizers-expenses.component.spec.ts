import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FertilizersExpensesComponent} from "./fertilizers-expenses.component";

describe('FertilizersExpensesComponent', () => {
    let component: FertilizersExpensesComponent;
    let fixture: ComponentFixture<FertilizersExpensesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [FertilizersExpensesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FertilizersExpensesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
