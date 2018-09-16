import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PesticideComponent } from './harmful-organism.component';

describe('PesticideComponent', () => {
  let component: PesticideComponent;
  let fixture: ComponentFixture<PesticideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PesticideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
