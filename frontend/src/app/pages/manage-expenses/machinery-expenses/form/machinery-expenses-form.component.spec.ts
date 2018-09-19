import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineryExpensesFormComponent } from './machinery-expenses-form.component';

describe('MachineryExpensesFormComponent', () => {
  let component: MachineryExpensesFormComponent;
  let fixture: ComponentFixture<MachineryExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineryExpensesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineryExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
