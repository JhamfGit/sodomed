import { EnumTypesIdentification, TypesIdentification } from "./types-identification";

export class TypeIdentificationsExport {
    private typesIds: { label: string, value: TypesIdentification }[];

    constructor() {
        this.typesIds = [
            { label: "Selecciona el tipo de documento", value: null },
            { label: "Cédula de Ciudadanía", value: EnumTypesIdentification.CC },
            { label: "Tarjeta de Identidad", value: EnumTypesIdentification.TI },
            { label: "NIT", value: EnumTypesIdentification.NIT },
            { label: "RUT", value: EnumTypesIdentification.RUT },
            { label: "Cédula de Extranjería", value: EnumTypesIdentification.CE },
            { label: "Pasaporte", value: EnumTypesIdentification.PA },
            { label: "Permiso por Protección Temporal", value: EnumTypesIdentification.PT },
            { label: "Salvo Conducto", value: EnumTypesIdentification.SC },
            { label: "Permiso Especial de Permanencia", value: EnumTypesIdentification.PEP },
        ]
    }

    get typesIdsReturn(): { label: string, value: TypesIdentification }[] {
        return this.typesIds;
    }

    get typeIdsEmployee(): { label: string, value: TypesIdentification }[] {
        return this.typesIds.filter(
            typeid => typeid.value === EnumTypesIdentification.CC || 
            typeid.value === EnumTypesIdentification.CE ||
            typeid.value === EnumTypesIdentification.PA ||
            typeid.value === EnumTypesIdentification.PT
        ); 
    }

    get typesIdsReturnOnlyToLogin(): { label: string, value: TypesIdentification }[] {
        return this.typesIds.filter(type => type.value !== EnumTypesIdentification.TI);
    }
}
