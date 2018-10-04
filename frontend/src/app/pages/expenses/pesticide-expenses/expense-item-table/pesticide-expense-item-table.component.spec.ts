import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PesticideExpenseItemTableComponent } from './pesticide-expense-item-table.component';

describe('PesticideExpenseItemTableComponent', () => {
  let component: PesticideExpenseItemTableComponent;
  let fixture: ComponentFixture<PesticideExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PesticideExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PesticideExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
