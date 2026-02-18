import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponseData } from '@core/response/response-data';
import { catchError, Observable, throwError } from 'rxjs';
import { MedicalRequestData } from '../models/medical-request-data';
import { Router } from '@angular/router';
import { ResponseDataList } from '@core/response/response-data-list';
import { LisBeneficiary } from 'src/app/affiliates/models/beneficiary';
import { HttpBaseService } from '@core/services/http-base.service';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }


  getBeneficiaryByClient(identificationClient: string): Observable<ResponseDataList<LisBeneficiary>> {
    return this.httpClient.get<ResponseDataList<LisBeneficiary>>(this.getUrlFor("/by-client"), { params: { identificationClient } })
      .pipe(
        catchError(this.handleError)
      )
  }

  get serviceName() {
    return 'beneficiary'
  }
}
