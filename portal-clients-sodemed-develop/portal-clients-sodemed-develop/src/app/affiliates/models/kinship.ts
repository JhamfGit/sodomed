export type Kinship = typeof EnumKinship[keyof typeof EnumKinship]

export enum EnumKinship {
    Son = 'son',
    Partner = 'partner',
    Father = 'father',
    Mother = 'mother'
}