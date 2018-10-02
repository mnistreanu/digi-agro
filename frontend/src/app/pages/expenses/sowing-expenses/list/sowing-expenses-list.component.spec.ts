import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SowingExpensesListComponent} from './sowing-expenses-list.component';

describe('SowingExpensesListComponent', () => {
    let component: SowingExpensesListComponent;
    let fixture: ComponentFixture<SowingExpensesListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SowingExpensesListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SowingExpensesListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
