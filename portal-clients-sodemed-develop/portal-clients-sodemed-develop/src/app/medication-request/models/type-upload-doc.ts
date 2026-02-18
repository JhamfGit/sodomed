
export type TypeUploadDoc = typeof EnumUploadDoc[keyof typeof EnumUploadDoc]
export enum EnumUploadDoc {
    docIndentity = 'docIdentity',
    docMedicalOrder = 'docMedicalOrder',
    docClinicalHistory = 'docClinicalHistory'
}