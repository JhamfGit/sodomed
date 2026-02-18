

    export interface RoleEmployee{
        readonly  id: number;
        name: string;
        code: string;
        description: string;
        status: StatusRoleType;
        permissions: string[];
    }
    

export enum EnumStatusRole {
    Active = 'active',
    Inactive = 'inactive'
}
export type StatusRoleType =typeof EnumStatusRole[keyof typeof EnumStatusRole] | keyof typeof EnumStatusRole;