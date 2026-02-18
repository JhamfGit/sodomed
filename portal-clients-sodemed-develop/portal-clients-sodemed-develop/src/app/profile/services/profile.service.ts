import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterUserClient } from '@auth/models/register-user-client';
import { ResponseData } from '@core/response/response-data';
import { HttpBaseService } from '@core/services/http-base.service';
import { catchError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService extends HttpBaseService {

  constructor(private httpClient: HttpClient, private router: Router) {
    super();
  }


  update(user : RegisterUserClient, userId: number): Observable<ResponseData<RegisterUserClient>> {
    return this.httpClient.put<ResponseData<RegisterUserClient>>(this.getUrlFor(`/update/${userId}`),user)
      .pipe(
        catchError(this.handleError)
      )
  }


  delete(user : RegisterUserClient, userId: number): Observable<ResponseData<RegisterUserClient>> {
    return this.httpClient.delete<ResponseData<RegisterUserClient>>(this.getUrlFor(`/${userId}`))
      .pipe(
        catchError(this.handleError)
      )
  }

  get serviceName() {
    return 'auth'
  }
}

