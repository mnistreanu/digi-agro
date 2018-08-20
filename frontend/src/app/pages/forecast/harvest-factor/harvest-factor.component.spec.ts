import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HarvestFactorComponent } from './harvest-factor.component';

describe('HarvestFactorComponent', () => {
  let component: HarvestFactorComponent;
  let fixture: ComponentFixture<HarvestFactorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HarvestFactorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HarvestFactorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
