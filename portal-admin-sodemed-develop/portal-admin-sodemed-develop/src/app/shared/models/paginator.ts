export class Paginator {
    page: number;
    row: number;
    totalRecords: number;
    totalPages: number;
    constructor(row: number = 10) {
        this.page = 1
        this.row = row
        this.totalRecords = 0
        this.totalRecords = 0
        this.totalPages = 0;
    }
}
