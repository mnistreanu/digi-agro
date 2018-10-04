import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FertilizerExpenseItemTableComponent } from './fertilizer-expense-item-table.component';

describe('FertilizerExpenseItemTableComponent', () => {
  let component: FertilizerExpenseItemTableComponent;
  let fixture: ComponentFixture<FertilizerExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FertilizerExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FertilizerExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
