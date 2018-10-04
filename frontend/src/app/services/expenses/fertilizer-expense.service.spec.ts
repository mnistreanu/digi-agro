import { TestBed, inject } from '@angular/core/testing';

import { FertilizerExpenseService } from './fertilizer-expense.service';

describe('FertilizerExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FertilizerExpenseService]
    });
  });

  it('should be created', inject([FertilizerExpenseService], (service: FertilizerExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
