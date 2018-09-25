import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ExpenseCategoryTreeComponent} from './expense-category-tree.component';

describe('ExpenseCategoryTreeComponent', () => {
    let component: ExpenseCategoryTreeComponent;
    let fixture: ComponentFixture<ExpenseCategoryTreeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ExpenseCategoryTreeComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ExpenseCategoryTreeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
