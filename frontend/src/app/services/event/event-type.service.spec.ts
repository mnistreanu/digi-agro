import { TestBed, inject } from '@angular/core/testing';

import { EventTypeService } from './parcel-event.service';

describe('EventTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EventTypeService]
    });
  });

  it('should be created', inject([EventTypeService], (service: EventTypeService) => {
    expect(service).toBeTruthy();
  }));
});
