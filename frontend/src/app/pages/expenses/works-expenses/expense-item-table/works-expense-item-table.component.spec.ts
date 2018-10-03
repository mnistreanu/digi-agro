import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorksExpenseItemTableComponent } from './works-expense-item-table.component';

describe('WorksExpenseItemTableComponent', () => {
  let component: WorksExpenseItemTableComponent;
  let fixture: ComponentFixture<WorksExpenseItemTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorksExpenseItemTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorksExpenseItemTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
