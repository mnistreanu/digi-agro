import {MachineModel} from '../../../manage-machines/machine/machine.model';
import {EmployeeModel} from '../../../employee/employee/employee.model';
export class MachineryExpenseListModel {
  expenseId: number;
  expenseCategoryId: number;
  expenseDate: string;
  machines: MachineModel[];
  machinesString: string;
  employees: EmployeeModel[];
  employeesString: string;
  sparePart: string;
  sparePartPrice: number;

}
