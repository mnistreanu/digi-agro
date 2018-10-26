import {EmployeeModel} from '../../../enterprise/manage-employees/employee/employee.model';
import {MachineModel} from '../../../enterprise/manage-machines/machine/machine.model';
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
