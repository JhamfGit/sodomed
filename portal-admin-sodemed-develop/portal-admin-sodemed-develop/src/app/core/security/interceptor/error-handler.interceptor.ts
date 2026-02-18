import { HttpErrorResponse, HttpEventType, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { SessionService } from '../services/session-service.service';
import { ErrorResponse } from '@core/response/error-response';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';

/**
 * interceptor encargado de manejar el errro de vencimiento de la session
 * @param req 
 * @param next 
 * @returns 
 */
export const ErrorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const sessionService = inject(SessionService)
  const notificationService = inject(NotificationService)
  return next(req).pipe(
    catchError(error => {
      if (error instanceof HttpErrorResponse) {
        const errorResponse = error.error
        const errorNotify = errorResponse.error as ErrorResponse
        switch (error.status) {
          case 401:
            sessionService.clearSession();
            sessionService.logout()
            notificationService.toasErrorI18n(errorNotify)
            break;
          case 403:
            sessionService.clearSession();
            sessionService.logout()
            notificationService.toasErrorI18n(errorNotify)
            break;

          default:
            return throwError(() => error);
        }
      }
      return throwError(() => error);
    })

  );
};


