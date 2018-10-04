import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FertilizerExpensesListComponent} from './fertilizer-expenses-list.component';

describe('FertilizerExpensesListComponent', () => {
    let component: FertilizerExpensesListComponent;
    let fixture: ComponentFixture<FertilizerExpensesListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [FertilizerExpensesListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FertilizerExpensesListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
