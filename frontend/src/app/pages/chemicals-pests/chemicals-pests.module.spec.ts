import { ChemicalsPestsModule } from './chemicals-pests.module';

describe('ChemicalsPestsModule', () => {
  let employeeModule: ChemicalsPestsModule;

  beforeEach(() => {
    employeeModule = new ChemicalsPestsModule();
  });

  it('should create an instance', () => {
    expect(employeeModule).toBeTruthy();
  });
});
