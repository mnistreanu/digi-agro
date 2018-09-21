import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuelExpensesFormComponent } from './fuel-expenses-form.component';

describe('FuelExpensesFormComponent', () => {
  let component: FuelExpensesFormComponent;
  let fixture: ComponentFixture<FuelExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuelExpensesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuelExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
