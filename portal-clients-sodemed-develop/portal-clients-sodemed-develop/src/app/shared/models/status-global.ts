
export enum EnumStatusGlobal{
    Active = 'active',
    Inactive = 'inactive'
}
export type StatusGlobalType =typeof EnumStatusGlobal[keyof typeof EnumStatusGlobal] | keyof typeof EnumStatusGlobal;