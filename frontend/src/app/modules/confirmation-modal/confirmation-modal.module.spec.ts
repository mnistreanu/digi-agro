import { ConfirmationModalModule } from './confirmation-modal.module';

describe('ConfirmationModalModule', () => {
  let confirmationModalModule: ConfirmationModalModule;

  beforeEach(() => {
    confirmationModalModule = new ConfirmationModalModule();
  });

  it('should create an instance', () => {
    expect(confirmationModalModule).toBeTruthy();
  });
});
