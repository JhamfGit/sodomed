import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterUser } from '@auth/models/register-user';
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


  update(user : RegisterUser, userId: number): Observable<ResponseData<RegisterUser>> {
    return this.httpClient.put<ResponseData<RegisterUser>>(this.getUrlFor(`/update/${userId}`),user)
      .pipe(
        catchError(this.handleError)
      )
  }


  delete(user : RegisterUser, userId: number): Observable<ResponseData<RegisterUser>> {
    return this.httpClient.delete<ResponseData<RegisterUser>>(this.getUrlFor(`/${userId}`))
      .pipe(
        catchError(this.handleError)
      )
  }

  get serviceName() {
    return 'auth'
  }
}

