import { TypesIdentification } from "@shared/models/types-identification";

export interface SysUser {
    accesToken: string;
    name: string;
    lastName: string;
    email: string;
    identification: string;
    userId: number;
    userType: UserType;
    countryDialCode: `+${string}`;
    typeIdentification: TypesIdentification
    phone?: string;
    permissions: string[];
}





export enum EnumUserType {
    Employee = 'employee',
    Client = 'client',
}
export type UserType = typeof EnumUserType[keyof typeof EnumUserType]