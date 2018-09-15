import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HarmfulOrganismComponent } from './harmful-organism.component';

describe('HarmfulOrganismComponent', () => {
  let component: HarmfulOrganismComponent;
  let fixture: ComponentFixture<HarmfulOrganismComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HarmfulOrganismComponent ]
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
