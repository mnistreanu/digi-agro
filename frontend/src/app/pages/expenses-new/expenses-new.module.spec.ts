import { ExpensesNewModule } from './expenses-new.module';

describe('ExpensesNewModule', () => {
  let expensesNewModule: ExpensesNewModule;

  beforeEach(() => {
    expensesNewModule = new ExpensesNewModule();
  });

  it('should create an instance', () => {
    expect(expensesNewModule).toBeTruthy();
  });
});
