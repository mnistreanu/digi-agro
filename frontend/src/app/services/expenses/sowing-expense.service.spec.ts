import { TestBed, inject } from '@angular/core/testing';

import { SowingExpenseService } from './sowing-expense.service';

describe('SowingExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SowingExpenseService]
    });
  });

  it('should be created', inject([SowingExpenseService], (service: SowingExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
