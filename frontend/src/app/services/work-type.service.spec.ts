import { TestBed, inject } from '@angular/core/testing';

import { WorkTypeService } from './work-type.service';

describe('WorkTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorkTypeService]
    });
  });

  it('should be created', inject([WorkTypeService], (service: WorkTypeService) => {
    expect(service).toBeTruthy();
  }));
});
