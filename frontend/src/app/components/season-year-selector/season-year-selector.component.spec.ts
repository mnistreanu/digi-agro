import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeasonYearSelectorComponent } from './season-year-selector.component';

describe('SeasonYearSelectorComponent', () => {
  let component: SeasonYearSelectorComponent;
  let fixture: ComponentFixture<SeasonYearSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeasonYearSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeasonYearSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
