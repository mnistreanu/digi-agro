import { ManageMachinesModule } from './manage-machines.module';

describe('ManageMachinesModule', () => {
  let manageMachinesModule: ManageMachinesModule;

  beforeEach(() => {
    manageMachinesModule = new ManageMachinesModule();
  });

  it('should create an instance', () => {
    expect(manageMachinesModule).toBeTruthy();
  });
});
