import { TestBed, inject } from '@angular/core/testing';

import { GlobalHttpInterceptorService } from './global-http-interceptor.service';

describe('GlobalHttpInterceptorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GlobalHttpInterceptorService]
    });
  });

  it('should be created', inject([GlobalHttpInterceptorService], (service: GlobalHttpInterceptorService) => {
    expect(service).toBeTruthy();
  }));
});
