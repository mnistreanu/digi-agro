import { TestBed, inject } from '@angular/core/testing';

import { ParcelEventService } from './parcel-event.service';

describe('ParcelEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ParcelEventService]
    });
  });

  it('should be created', inject([ParcelEventService], (service: ParcelEventService) => {
    expect(service).toBeTruthy();
  }));
});
