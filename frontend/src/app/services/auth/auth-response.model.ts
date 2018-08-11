import {ListItem} from '../../interfaces/list-item.interface';

export class AuthResponseModel {

    username: string;
    token: string;
    authorities: string[];
    logoUrl: string;
    language: string;
    tenants: ListItem[];

}
