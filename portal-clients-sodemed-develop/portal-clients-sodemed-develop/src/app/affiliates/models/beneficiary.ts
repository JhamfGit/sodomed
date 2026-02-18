import { TypesIdentification } from "@shared/models/types-identification";
import { Kinship } from "./kinship";
import { StatusUserType } from "./status-user";
import { TypeClient } from "@core/models/type-client";
import { StatusGlobalType } from "@shared/models/status-global";

export interface Beneficiary {
  readonly id?: number;
  typeIdentification: TypesIdentification;
  identification: string;
  name: string;
  lastName: string;
  phone: string;
  countryDialCode: string;
  email: string;
  kinship: Kinship;
  typeClient: TypeClient
  status?:StatusGlobalType;
}

export interface LisBeneficiary extends Beneficiary {
  status: StatusUserType;

}
