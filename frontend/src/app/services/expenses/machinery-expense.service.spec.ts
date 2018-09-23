import { TestBed, inject } from '@angular/core/testing';

import { MachineryExpenseService } from './machinery-expense.service';

describe('MachineryExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MachineryExpenseService]
    });
  });

  it('should be created', inject([MachineryExpenseService], (service: MachineryExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
