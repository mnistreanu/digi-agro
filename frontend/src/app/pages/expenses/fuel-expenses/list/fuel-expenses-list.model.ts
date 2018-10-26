import {MachineModel} from '../../../enterprise/manage-machines/machine/machine.model';
import {EmployeeModel} from '../../../enterprise/manage-employees/employee/employee.model';
import {FuelExpenseItemModel} from '../expense-item-table/fuel-expense-item.model';

export class FuelExpensesListModel {
  date: string;
  title: string;
  machines: MachineModel[];
  machinesString: string;
  employees: EmployeeModel[];
  employeesString: string;
  fuels: FuelExpenseItemModel[];

  // unitOfMeasure: string;
  // diesel: number;
  // oil: number;
  // solidol: number;
  // negrol: number;
}
