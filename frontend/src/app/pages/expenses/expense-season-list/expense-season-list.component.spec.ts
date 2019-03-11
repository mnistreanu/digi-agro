import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {ExpenseSeasonListComponent} from './expense-season-list.component';

describe('ExpenseSeasonListComponent', () => {
    let component: ExpenseSeasonListComponent;
    let fixture: ComponentFixture<ExpenseSeasonListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ExpenseSeasonListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ExpenseSeasonListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
