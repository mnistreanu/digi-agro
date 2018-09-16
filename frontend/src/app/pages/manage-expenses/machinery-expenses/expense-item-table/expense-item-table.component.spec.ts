import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseItemTableComponent } from './expense-item-table.component';

describe('ExpenseItemTableComponent', () => {
  let component: ExpenseItemTableComponent;
  let fixture: ComponentFixture<ExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
