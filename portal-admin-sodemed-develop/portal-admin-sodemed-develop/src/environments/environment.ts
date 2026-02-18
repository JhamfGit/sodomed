// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import { EnvironmentBase } from "@core/services/http-base";

export const environment: EnvironmentBase = {
  production: false,
  services: {
    endpoint: 'http://localhost:8080/api',
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

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
