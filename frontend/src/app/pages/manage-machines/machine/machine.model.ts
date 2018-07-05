export class MachineModel {

    id: number;
    identifier: string;
    name: string;

    type: string;

    owner: string;
    brand: string;

    fabricationDate: Date;
    fabricationCountry: string;

    motorType: string;
    consumption: number;
    power: number;

    speedOnRoad: number;
    speedInWork: number;

    workTypes: string[];

}