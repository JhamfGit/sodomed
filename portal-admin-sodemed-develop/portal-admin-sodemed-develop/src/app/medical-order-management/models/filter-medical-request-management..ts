import { StatusOrderType } from "@shared/models/status-order";

export interface FilterMedicalRequestManagement {
    startDate: Date;
    endDate: Date;
    statusOrder: StatusOrderType;
    identification: string;
}
