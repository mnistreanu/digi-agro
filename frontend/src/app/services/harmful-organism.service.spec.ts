import { TestBed, inject } from '@angular/core/testing';

import { HarmfulOrganismService } from './employee.service';

describe('HarmfulOrganismService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HarmfulOrganismService]
    });
  });

  it('should be created', inject([HarmfulOrganismService], (service: HarmfulOrganismService) => {
    expect(service).toBeTruthy();
  }));
});
