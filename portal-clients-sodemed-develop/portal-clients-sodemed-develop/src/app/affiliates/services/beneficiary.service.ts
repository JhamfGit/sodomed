import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ResponseData } from '@core/response/response-data';
import { catchError, Observable, pipe, throwError } from 'rxjs';
import { Beneficiary, LisBeneficiary } from '../models/beneficiary';
import { ResponseDataList } from '@core/response/response-data-list';
import { ErrorResponse } from '@core/response/error-response';
import { HttpBaseService } from '@core/services/http-base.service';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }


  createBeneficiary(clientId: string, benefi: Beneficiary): Observable<ResponseData<any>> {
    const newBeneficiary = {
      ...benefi,
      clientId: clientId
    }
    const beneficiary = newBeneficiary
    return this.httpClient.post<ResponseData<any>>(this.getUrlFor("/"), beneficiary)
      .pipe(
        catchError(this.handleError)
      )

  }

  getBeneficiaryByClient(identificationClient: string): Observable<ResponseDataList<LisBeneficiary>> {
    return this.httpClient.get<ResponseDataList<LisBeneficiary>>(this.getUrlFor("/by-client"), { params: { identificationClient } })
      .pipe(
        catchError(this.handleError)
      )
  }

  updateBeneficiary(beneficiary : Beneficiary): Observable<ResponseData<Beneficiary>> {
    return this.httpClient.put<ResponseData<Beneficiary>>(this.getUrlFor(`/${beneficiary?.id}`),beneficiary)
      .pipe(
        catchError(this.handleError)
      )
  }


  deleteBeneficiary(beneficiary : Beneficiary): Observable<ResponseData<Beneficiary>> {
    return this.httpClient.delete<ResponseData<Beneficiary>>(this.getUrlFor(`/${beneficiary?.id}`))
      .pipe(
        catchError(this.handleError)
      )
  }

  get serviceName() {
    return 'beneficiary'
  }


}

