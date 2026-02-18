import { ResponseData } from "./response-data";

export class ResponsePaginable<T> extends ResponseData<T[]> {
    public allRecords: number;
    public allPages: number;
    public page: number;
    public recordsPerPage: number;
}
