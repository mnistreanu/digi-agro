import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SowingExpenseItemTableComponent } from './machinery-expense-item-table.component';

describe('SowingExpenseItemTableComponent', () => {
  let component: SowingExpenseItemTableComponent;
  let fixture: ComponentFixture<SowingExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SowingExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SowingExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
