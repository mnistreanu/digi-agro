import { ParcelModule } from './parcel.module';

describe('ParcelModule', () => {
  let parcelModule: ParcelModule;

  beforeEach(() => {
    parcelModule = new ParcelModule();
  });

  it('should create an instance', () => {
    expect(parcelModule).toBeTruthy();
  });
});
