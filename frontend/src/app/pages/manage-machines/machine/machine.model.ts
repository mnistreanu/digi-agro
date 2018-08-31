import {AgroWorkTypeModel} from '../../reminder/agro-work-type.model';
export class MachineModel {

    id: number;
    identifier: string;
    name: string;

    type: string;

    brand: string;

    fabricationDate: Date;
    fabricationCountry: string;

    motorType: string;
    consumption: number;
    power: number;

    speedOnRoad: number;
    speedInWork: number;

    workTypes: AgroWorkTypeModel[];
}
