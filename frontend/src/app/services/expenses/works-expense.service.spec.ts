import { TestBed, inject } from '@angular/core/testing';

import { WorksExpenseService } from './works-expense.service';

describe('WorksExpenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorksExpenseService]
    });
  });

  it('should be created', inject([WorksExpenseService], (service: WorksExpenseService) => {
    expect(service).toBeTruthy();
  }));
});
