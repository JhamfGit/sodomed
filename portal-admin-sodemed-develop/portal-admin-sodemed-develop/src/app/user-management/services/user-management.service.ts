import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, pipe, throwError } from 'rxjs';
import { Beneficiary, LisBeneficiary } from '../models/beneficiary';
import { HttpBaseService } from '@core/services/http-base.service';
import { ResponseData } from '@core/response/response-data';
import { ResponseDataList } from '@core/response/response-data-list';
import { UserEmployee } from '@auth/models/register-user';
import { BuildQueryParams } from '@shared/models/build-params';
import { ResponsePaginable } from '@core/response/response-paginable';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }


  createUserEmployye(userEmployee: UserEmployee): Observable<ResponseData<any>> {
    return this.httpClient.post<ResponseData<any>>(this.getUrlFor("/"), userEmployee)
      .pipe(
        catchError(this.handleError)
      )

  }

  getUserEmployye(filter: any,page: number, row: number): Observable<ResponsePaginable<UserEmployee>> {
    const paramsReuquest = {
      page: page,
      size: row,
      identification: filter?.identification,
      id: filter?.idUser
    }
    const newPrams = BuildQueryParams.buildQueryObject(paramsReuquest)
    return this.httpClient.get<ResponsePaginable<UserEmployee>>(this.getUrlFor("/"), { params: newPrams })
      .pipe(
        catchError(this.handleError)
      )
  }

  deleteUserEmployee(id: number): Observable<ResponseData<any>> {
    return this.httpClient.delete<ResponseData<any>>(this.getUrlFor(`/${id}`))
      .pipe(
        catchError(this.handleError)
      )
  }
  activeUserEmployee(id: number): Observable<ResponseData<any>> {
    return this.httpClient.put<ResponseData<any>>(this.getUrlFor(`/active/${id}`),null)
      .pipe(
        catchError(this.handleError)
      )
  }


  get serviceName() {
    return 'employee'
  }
}

