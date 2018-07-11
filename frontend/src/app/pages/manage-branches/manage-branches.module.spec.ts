import { ManageBranchesModule } from './manage-branches.module';

describe('ManageBranchesModule', () => {
  let manageBranchesModule: ManageBranchesModule;

  beforeEach(() => {
    manageBranchesModule = new ManageBranchesModule();
  });

  it('should create an instance', () => {
    expect(manageBranchesModule).toBeTruthy();
  });
});
