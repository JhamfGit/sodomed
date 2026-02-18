import { TypesIdentification } from "@shared/models/types-identification";

export interface RegisterUserClient {
    typeIdentification: TypesIdentification;
    identification: string;
    name: string;
    lastName: string;
    phone: string;
    countryDialCode: string;
    email: string;
    password: string;
    userType?: UserType;
}



export enum EnumUserType {
    Employee = 'employee',
    Client = 'client',
}



export type UserType = typeof  EnumUserType[keyof typeof EnumUserType]