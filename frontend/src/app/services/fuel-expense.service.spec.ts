import { TestBed, inject } from '@angular/core/testing';

import { FuelExpenseService } from './machinery-expense.service';

describe('FuelExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FuelExpenseService]
    });
  });

  it('should be created', inject([FuelExpenseService], (service: FuelExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
