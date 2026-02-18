import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { ResponseData } from 'src/app/core/response/response-data';
import { SysUser } from '../models/sys-user';
import { RegisterUserClient } from '../models/register-user-client';
import { HttpBaseService } from '@core/services/http-base.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }

  registerUserClient(dataRegisterUserCLiente: RegisterUserClient): Observable<ResponseData<any>> {
    return this.httpClient.post<ResponseData<any>>(this.getUrlFor("/register"), dataRegisterUserCLiente)
      .pipe(
        catchError(this.handleError)
      );
  }


  get serviceName() {
    return 'auth'
  }
}

