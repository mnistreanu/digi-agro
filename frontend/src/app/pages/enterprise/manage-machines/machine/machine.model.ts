import {EmployeeModel} from '../../manage-employees/employee/employee.model';
import {MachineGroupModel} from '../../machine-groups/machine-group/machine-group.model';

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

    machineGroupModel: MachineGroupModel;
    machineGroupId: number;
}
