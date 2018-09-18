import { TestBed, inject } from '@angular/core/testing';

import { PesticideService } from '../employee.service';

describe('PesticideService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PesticideService]
    });
  });

  it('should be created', inject([PesticideService], (service: PesticideService) => {
    expect(service).toBeTruthy();
  }));
});
