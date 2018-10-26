import {MachineModel} from '../../../enterprise/manage-machines/machine/machine.model';
import {EmployeeModel} from '../../../enterprise/manage-employees/employee/employee.model';
export class MachineryExpensesListModel {
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
