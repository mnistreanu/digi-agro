import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MachineryExpensesListComponent} from "./machinery-expenses-list.component";

describe('MachineryExpensesListComponent', () => {
    let component: MachineryExpensesListComponent;
    let fixture: ComponentFixture<MachineryExpensesListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [MachineryExpensesListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MachineryExpensesListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
