import { StatusOrderType } from "@shared/models/status-order";

export interface MovementsMedicalRequest {
    readonly id: number;
    effDate: Date;
    idEmployee: number;
    nameEmployee: string;
    medicationOrder: number;
    statusOrder: StatusOrderType;
    observations: string;
}