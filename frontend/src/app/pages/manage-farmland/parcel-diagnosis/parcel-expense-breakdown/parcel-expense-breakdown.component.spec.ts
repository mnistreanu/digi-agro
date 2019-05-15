import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelExpenseBreakdownComponent } from './parcel-expense-breakdown.component';

describe('ParcelExpenseBreakdownComponent', () => {
  let component: ParcelExpenseBreakdownComponent;
  let fixture: ComponentFixture<ParcelExpenseBreakdownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelExpenseBreakdownComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelExpenseBreakdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
