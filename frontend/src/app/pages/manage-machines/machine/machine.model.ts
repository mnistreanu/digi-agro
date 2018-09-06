import {EmployeeModel} from '../../employee/employee/employee.model';

export class MachineModel {

    id: number;
    identifier: string;
    model: string;

    type: string;

    brand: string;

    fabricationYear: number;
    fabricationCountry: string;

    motorType: string;
    consumption: number;
    power: number;

    speedOnRoad: number;
    speedInWork: number;

    employees: EmployeeModel[];
    workTypes: number[];
}
