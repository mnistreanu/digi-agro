import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FertilizerExpensesFormComponent } from './fertilizer-expenses-form.component';

describe('FertilizerExpensesFormComponent', () => {
  let component: FertilizerExpensesFormComponent;
  let fixture: ComponentFixture<FertilizerExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FertilizerExpensesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FertilizerExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
