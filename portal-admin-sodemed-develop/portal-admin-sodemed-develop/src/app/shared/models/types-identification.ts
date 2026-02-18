
 export type TypesIdentification = typeof EnumTypesIdentification[keyof typeof EnumTypesIdentification];

 export enum EnumTypesIdentification {
    CC = 'CC',       // CEDULA DE CIUDADANÍA
    CE = 'CE',        // CÉDULA DE EXTRANJERÍA
    TI = 'TI',       // TARJETA DE IDENTIDAD
    NIT = 'NIT',     // NÚMERO DE IDENTIFICACIÓN TRIBUTARIA
    RUT = 'RUT',   // RUT
    PA = 'PA',     // PASAPORTE
    PT = 'PT',   // PERMISO POR PROTECCIÓN TEMPORAL
    SC = 'SC',   // SALVO CONDUCTO
    PEP = 'PEP',   // PERMISO ESPECIAL DE PERMANENCIA
}
