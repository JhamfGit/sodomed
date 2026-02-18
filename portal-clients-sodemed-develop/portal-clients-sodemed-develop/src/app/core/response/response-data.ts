export class ResponseData<T> {
    public message: string
    public data: T
    public code: number
    public success: boolean
    public typeResponse: string
    public error
}
