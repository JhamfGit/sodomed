export class BuildQueryParams {
    public static buildQueryObject<T extends Record<string, any>>(queryParams: T): T {
        const objectParams: Partial<T> = {}; // Usa Partial para que el objeto pueda ser del mismo tipo pero con propiedades opcionales

        Object.entries(queryParams).forEach(([key, value]) => {
            if (value !== undefined && value !== null && value !== '') {
                objectParams[key as keyof T] = typeof value === 'string' ? value.trim() : value;
            }
        });

        return objectParams as T; // Devuelve el nuevo objeto como tipo T
    }
}
