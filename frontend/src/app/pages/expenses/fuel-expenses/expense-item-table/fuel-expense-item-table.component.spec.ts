import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuelExpenseItemTableComponent } from './fuel-expense-item-table.component';

describe('FuelExpenseItemTableComponent', () => {
  let component: FuelExpenseItemTableComponent;
  let fixture: ComponentFixture<FuelExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuelExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuelExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
