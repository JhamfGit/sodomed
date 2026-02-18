export class ResponseDataList<T> {
    public message: string;
    public data: T[];
    public code: number;
    public success: boolean;
    public typeResponse: string;
}
