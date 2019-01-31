import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseListNewComponent } from './expense-list-new.component';

describe('ExpenseListNewComponent', () => {
  let component: ExpenseListNewComponent;
  let fixture: ComponentFixture<ExpenseListNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseListNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseListNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
