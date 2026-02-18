import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { ResponseData } from 'src/app/core/response/response-data';
import { SysUser } from '../models/sys-user';
import { ErrorResponse } from '@core/response/error-response';
import { HttpBaseService } from '@core/services/http-base.service';
import { TypesIdentification } from '@shared/models/types-identification';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }

  userLogin(login: string, password: string, typeIdentification: TypesIdentification): Observable<ResponseData<SysUser>> {
    return this.httpClient.post<ResponseData<SysUser>>(this.getUrlFor("/login"), { login, password, typeIdentification })
      .pipe(
        catchError(this.handleError)
      );
  }

  requestChangePassword(identification: string): Observable<ResponseData<any>> {
    return this.httpClient.post<ResponseData<any>>(this.getUrlFor("/forgot-password"), identification)
      .pipe(
        catchError(this.handleError)
      );
  }


  get serviceName() {
    return 'auth'
  }

}

