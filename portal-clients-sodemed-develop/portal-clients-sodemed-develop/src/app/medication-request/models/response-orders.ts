import { TypeClient } from "@core/models/type-client";
import { StatusOrder, StatusOrderType } from "@shared/models/status-order";

export interface ResponseOrders {
   readonly id?: number;
   readonly idMember?: number;
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
    statusOrder: StatusOrderType;
    active:boolean;
    nameEmployee:string;
    observations:string;
}