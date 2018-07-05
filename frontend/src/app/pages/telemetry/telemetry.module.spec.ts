import { TelemetryModule } from './telemetry.module';

describe('TelemetryModule', () => {
  let telemetryModule: TelemetryModule;

  beforeEach(() => {
    telemetryModule = new TelemetryModule();
  });

  it('should create an instance', () => {
    expect(telemetryModule).toBeTruthy();
  });
});
