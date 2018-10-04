import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PesticideExpensesFormComponent } from './pesticide-expenses-form.component';

describe('PesticideExpensesFormComponent', () => {
  let component: PesticideExpensesFormComponent;
  let fixture: ComponentFixture<PesticideExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PesticideExpensesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PesticideExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
