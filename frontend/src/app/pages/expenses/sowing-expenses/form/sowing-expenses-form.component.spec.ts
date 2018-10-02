import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SowingExpensesFormComponent } from './sowing-expenses-form.component';

describe('SowingExpensesFormComponent', () => {
  let component: SowingExpensesFormComponent;
  let fixture: ComponentFixture<SowingExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SowingExpensesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SowingExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
