export enum EnumTypeClient {
    Beneficiary = 'beneficiary',
    Contizing = 'contizing'
}


export type  TypeClient = keyof typeof EnumTypeClient