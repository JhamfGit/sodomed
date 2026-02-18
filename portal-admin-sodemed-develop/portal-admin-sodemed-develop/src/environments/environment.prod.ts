import { EnvironmentBase } from "@core/services/http-base";

export const environment:EnvironmentBase = {
  production: true,
  services: {
    endpoint: 'https://domicilios.saludmedcol.com/api',
    auth: {
      endpoint: "$root/auth"
    },
    beneficiary: {
      endpoint: "$root/beneficiary"
    },
    medication: {
      endpoint: "$root/medication"
    },
    employee:{
      endpoint: "$root/employee"
    },
    role:{
      endpoint: "$root/role"
    }
  }
};
