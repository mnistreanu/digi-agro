import { ParcelEventModule } from './parcel-event.module';

describe('ParcelEventModule', () => {
  let parcelEventModule: ParcelEventModule;

  beforeEach(() => {
    parcelEventModule = new ParcelEventModule();
  });

  it('should create an instance', () => {
    expect(parcelEventModule).toBeTruthy();
  });
});
