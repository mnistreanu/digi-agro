import { TestBed, inject } from '@angular/core/testing';
import {PestService} from './pest.service';


describe('PestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PestService]
    });
  });

  it('should be created', inject([PestService], (service: PestService) => {
    expect(service).toBeTruthy();
  }));
});
