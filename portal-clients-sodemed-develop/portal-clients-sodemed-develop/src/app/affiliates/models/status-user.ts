
// export type StatusUser= typeof EnumStatusUser[keyof typeof EnumStatusUser]
export enum EnumStatusUser {
    Active = 'active',
    Inactive = 'inactive'
}

export type StatusUserType = keyof typeof EnumStatusUser;
