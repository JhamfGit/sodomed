import { ResponseData } from "./response-data";

export class ResponsePaginable<T> extends ResponseData<T[]> {
    public totalElements: number;
    public totalPages: number;
    public number: number;
    public recordsPerPage: number;
    public size: number;
}
