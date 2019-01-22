import { TestBed, inject } from '@angular/core/testing';

import { MachineGroupService } from './machine-group.service';

describe('MachineGroupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MachineGroupService]
    });
  });

  it('should be created', inject([MachineGroupService], (service: MachineGroupService) => {
    expect(service).toBeTruthy();
  }));
});
