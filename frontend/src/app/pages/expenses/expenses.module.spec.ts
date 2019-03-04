import { ExpensesNewModule } from './expenses-new.module';

describe('ExpensesModule', () => {
  let expensesNewModule: ExpensesNewModule;

  beforeEach(() => {
    expensesNewModule = new ExpensesNewModule();
  });

  it('should create an instance', () => {
    expect(expensesNewModule).toBeTruthy();
  });
});
