import { ManageUsersModule } from './manage-users.module';

describe('ManageUsersModule', () => {
  let manageUsersModule: ManageUsersModule;

  beforeEach(() => {
    manageUsersModule = new ManageUsersModule();
  });

  it('should create an instance', () => {
    expect(manageUsersModule).toBeTruthy();
  });
});
