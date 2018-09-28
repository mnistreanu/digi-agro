import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineryExpenseItemTableComponent } from './machinery-expense-item-table.component';

describe('MachineryExpenseItemTableComponent', () => {
  let component: MachineryExpenseItemTableComponent;
  let fixture: ComponentFixture<MachineryExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineryExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineryExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
