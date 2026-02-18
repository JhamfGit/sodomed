import { TypesIdentification } from "@shared/models/types-identification";
import { RoleEmployee as Role } from "src/app/user-management/models/role";
import { StatusUserType } from "src/app/user-management/models/status-user";
export interface RegisterUser {
    readonly id: number;
    countryDialCode: string;
    email: string;
    identification: string;
    lastName: string;
    name: string;
    phone: string;
    role: Role;
    status: StatusUserType; // Puede ser un enum si tienes valores específicos
    typeIdentification: TypesIdentification;
    userType?: UserType;
}

export interface UserEmployee extends RegisterUser {

}


export enum EnumUserType {
    Employee = 'employee',
    Client = 'client',
    // Beneficiary = 'beneficiary'
}

export type UserType = typeof EnumUserType[keyof typeof EnumUserType]