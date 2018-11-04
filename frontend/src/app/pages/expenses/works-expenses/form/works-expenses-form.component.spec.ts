import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorksExpensesFormComponent } from './works-expenses-form.component';

describe('WorksExpensesFormComponent', () => {
  let component: WorksExpensesFormComponent;
  let fixture: ComponentFixture<WorksExpensesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorksExpensesFormComponent ]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorksExpensesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
