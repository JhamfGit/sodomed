
export enum EnumStatusUser {
    Active = 'active',
    Inactive = 'inactive'
}

export type StatusUserType = keyof typeof EnumStatusUser;
