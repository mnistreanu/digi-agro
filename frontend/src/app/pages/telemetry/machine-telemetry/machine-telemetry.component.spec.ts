import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineTelemetryComponent } from './machine-telemetry.component';

describe('MachineTelemetryComponent', () => {
  let component: MachineTelemetryComponent;
  let fixture: ComponentFixture<MachineTelemetryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineTelemetryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineTelemetryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
