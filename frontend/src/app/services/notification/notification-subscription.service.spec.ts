import { TestBed, inject } from '@angular/core/testing';

import { NotificationSubscriptionService } from './notification-subscription.service';

describe('NotificationSubscriptionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NotificationSubscriptionService]
    });
  });

  it('should be created', inject([NotificationSubscriptionService], (service: NotificationSubscriptionService) => {
    expect(service).toBeTruthy();
  }));
});
