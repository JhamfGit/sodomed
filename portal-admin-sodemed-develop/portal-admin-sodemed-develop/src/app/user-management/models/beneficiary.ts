import { TypesIdentification } from "@shared/models/types-identification";
import { Kinship } from "./kinship";
import { StatusUserType } from "./status-user";
import { UserType } from "@auth/models/register-user";
import { TypeClient } from "@core/models/type-client";

export interface Beneficiary {
  typeIdentification: TypesIdentification;
  identification: string;
  name: string;
  lastName: string;
  phone: string;
  countryDialCode: string;
  email: string;
  kinship: Kinship;
  typeClient : TypeClient
}

export interface  LisBeneficiary extends Beneficiary  {
  status: StatusUserType;

}
