import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PesticideExpensesListComponent} from './pesticide-expenses-list.component';

describe('PesticideExpensesListComponent', () => {
    let component: PesticideExpensesListComponent;
    let fixture: ComponentFixture<PesticideExpensesListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [PesticideExpensesListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(PesticideExpensesListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
