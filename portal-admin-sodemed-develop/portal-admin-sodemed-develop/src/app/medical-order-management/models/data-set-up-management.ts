import { StatusOrderType } from "@shared/models/status-order";
import { ResponseOrders } from "./response-orders";

export interface DataSetUpManagement extends PartialSend  {
    stateOrderByAdmin: StatusOrderType
    descriptionByAdmin: string,
}

export interface StatusChangeRequestData extends PartialSend {
    idEmployee: number,
    nameEmployee: string,
    statusOrder: StatusOrderType;
    comments: string;
}


export interface PartialSend {
    partial: boolean;
    totalPending: number;
    detailsPending: string;
}