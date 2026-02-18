import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, pipe, throwError } from 'rxjs';
import { Beneficiary, LisBeneficiary } from '../models/beneficiary';
import { HttpBaseService } from '@core/services/http-base.service';
import { ResponseData } from '@core/response/response-data';
import { ResponseDataList } from '@core/response/response-data-list';
import { UserEmployee } from '@auth/models/register-user';
import { EnumStatusRole, RoleEmployee, StatusRoleType } from '../models/role';

@Injectable({
  providedIn: 'root'
})
export class RoleUserService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }

  getRoles(): Observable<ResponseDataList<RoleEmployee>> {
    return this.httpClient.get<ResponseDataList<RoleEmployee>>(this.getUrlFor("/"))
      .pipe(
        catchError(this.handleError)
      )
  }

  getRolesByStatus(statusRol:StatusRoleType = EnumStatusRole.Active ): Observable<ResponseDataList<RoleEmployee>> {
    return this.httpClient.get<ResponseDataList<RoleEmployee>>(this.getUrlFor("/by-status"),{params:{status:statusRol}})
      .pipe(
        catchError(this.handleError)
      )
  }

  createRole(roleEmployee: RoleEmployee): Observable<ResponseDataList<RoleEmployee>> {
    return this.httpClient.post<ResponseDataList<RoleEmployee>>(this.getUrlFor("/"), roleEmployee)
      .pipe(
        catchError(this.handleError)
      )
  }
  updateRole(roleEmployee: RoleEmployee): Observable<ResponseDataList<RoleEmployee>> {
    return this.httpClient.put<ResponseDataList<RoleEmployee>>(this.getUrlFor(`/${roleEmployee.id}`), roleEmployee)
      .pipe(
        catchError(this.handleError)
      )
  }

  deleteRole(roleEmployee: RoleEmployee): Observable<ResponseDataList<RoleEmployee>> {
    return this.httpClient.delete<ResponseDataList<RoleEmployee>>(this.getUrlFor(`/${roleEmployee.id}`))
      .pipe(
        catchError(this.handleError)
      )
  }

  getAvailablePermissions (): Observable<ResponseDataList<string>> {
    return this.httpClient.get<ResponseDataList<string>>(this.getUrlFor("/permissions"))
      .pipe(
        catchError(this.handleError)
      )
  }

  get serviceName() {
    return 'role'
  }
}

