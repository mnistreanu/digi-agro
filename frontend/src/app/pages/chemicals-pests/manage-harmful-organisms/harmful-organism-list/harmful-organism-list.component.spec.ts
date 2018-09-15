import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HarmfulOrganismListComponent } from './harmful-organism-list.component';

describe('HarmfulOrganismListComponent', () => {
  let component: HarmfulOrganismListComponent;
  let fixture: ComponentFixture<HarmfulOrganismListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HarmfulOrganismListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HarmfulOrganismListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
