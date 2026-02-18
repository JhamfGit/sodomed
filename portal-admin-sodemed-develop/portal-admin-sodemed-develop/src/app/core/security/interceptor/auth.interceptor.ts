import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SessionService } from '../services/session-service.service';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
    // Inject the current `AuthService` and use it to get an authentication token:
    const authToken = inject(SessionService)
    // Clone the request to add the authentication header.
    if(authToken.isLoggedIn){
      const newReqClone = req.clone({
        setHeaders: {
          Authorization:`Bearer ${authToken.sessionToken}`,
        }
      });
      return next(newReqClone);
    }
  
  return next(req);
};
