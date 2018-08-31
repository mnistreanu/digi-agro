export class MachineModel {

    id: number;
    identifier: string;
    name: string;

    type: string;

    brand: string;

    fabricationYear: number;
    fabricationCountry: string;

    motorType: string;
    consumption: number;
    power: number;

    speedOnRoad: number;
    speedInWork: number;

    employees: number[];
    workTypes: number[];
}
