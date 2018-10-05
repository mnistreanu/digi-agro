import { TestBed, inject } from '@angular/core/testing';

import { PesticideExpenseService } from './pesticide-expense.service';

describe('PesticideExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PesticideExpenseService]
    });
  });

  it('should be created', inject([PesticideExpenseService], (service: PesticideExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
