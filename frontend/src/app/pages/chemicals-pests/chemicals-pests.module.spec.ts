import { ChemicalsPestsModule } from './chemicals-pests.module';

describe('ChemicalsPestsModule', () => {
  let chemicalsPestsModule: ChemicalsPestsModule;

  beforeEach(() => {
    chemicalsPestsModule = new ChemicalsPestsModule();
  });

  it('should create an instance', () => {
    expect(chemicalsPestsModule).toBeTruthy();
  });
});
