import { TestBed, inject } from '@angular/core/testing';
import {MachineTelemetryService} from "./machine-telemetry.service";


describe('MachineTelemetryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MachineTelemetryService]
    });
  });

  it('should be created', inject([MachineTelemetryService], (service: MachineTelemetryService) => {
    expect(service).toBeTruthy();
  }));
});
