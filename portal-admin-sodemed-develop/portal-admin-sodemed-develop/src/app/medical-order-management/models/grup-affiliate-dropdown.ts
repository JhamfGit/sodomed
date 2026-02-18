export interface Affiliate {
    label: string; // Nombre de la ciudad
    value: any; // Valor de la ciudad
}

export interface GrupAffiliateDropdown {
    label: string; // Nombre del país
    value: string; // Valor del país
    items: Affiliate[]; // Lista de ciudades asociadas al país
}
