import {EmployeeModel} from '../../../employee/employee/employee.model';
import {MachineModel} from '../../../manage-machines/machine/machine.model';
export class WorksExpensesListModel {
    date: string;
    expenseId: number;
    workType: string;
    machines: MachineModel[];
    machinesString: string;
    employees: EmployeeModel[];
    employeesString: string;
    crop: string;
    unitOfMeasure: string;
    quantity: number;
    quantityNorm: number;
    quantityDefacto: number;
    price1Norm: number;
    sum: number;
}
