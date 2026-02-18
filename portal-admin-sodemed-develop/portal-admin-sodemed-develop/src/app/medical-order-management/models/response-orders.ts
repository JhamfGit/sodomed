import { TypeClient } from "@core/models/type-client";
import { StatusOrder } from "@shared/models/status-order";

export interface ResponseOrders {
    readonly id?: number;
    readonly idMember?: number;
    readonly active?:boolean;
    codigo: string;
    urlIdentificationFile: string;
    urlMedicationOrderFile: string;
    urlMedicalHistoryFile: string;
    comments: string;
    homeAddress: string;
    homeCity: string;
    urlFiles: string;
    userOrderIdentification: string;
    userOrderName: string;
    typeClient: TypeClient;
    creationDate: Date;
    district: string;
    furtherDirectionIndication: string;
    statusOrder: StatusOrder;
    partial?: boolean;
    totalPending?: number;
    detailsPending?: string;
    observations?:string;
    nameEmployee?:string;
    lastModificationDate?:string;
    idEmployee?:number;
}

export interface ResponseOrdersToDownload extends ResponseOrders {
    userCreatePhone:string;
    userCreateEmail:string;
    userCreateCountryDialCode:string;
}