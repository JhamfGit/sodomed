import { EnumKinship, Kinship } from "./kinship";

export class KinshipsOptions {
    private kinshipOptions: { label: string, value: Kinship }[];

    constructor() {
        this.kinshipOptions = [
            { label: "Selecciona el parentesco", value: null },
            { label: "Hijo/a", value: EnumKinship.Son },
            { label: "Pareja", value: EnumKinship.Partner },
            { label: "Padre", value: EnumKinship.Father },
            { label: "Madre", value: EnumKinship.Mother }
        ];
    }

    get kinshipOptionsReturn(): { label: string, value: Kinship }[] {
        return this.kinshipOptions;
    }
}