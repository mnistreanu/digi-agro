export class UserModel {

    id: number;
    username: string;
    password: string;
    email: string;
    idnp: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    address: string;
    phone: string;
    mobilePhone: string;
    roleName: string;

    tenants: number[];
    branches: number[];

    logoUrl: string;

    language: string;

}
