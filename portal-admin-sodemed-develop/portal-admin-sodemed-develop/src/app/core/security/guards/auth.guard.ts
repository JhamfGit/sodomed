import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionService } from '../services/session-service.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authToken = inject(SessionService);
  const router = inject(Router)
  if (authToken.isLoggedIn) {
    return true
  } else {
    router.navigate(['/auth/login'], {
      queryParams: {
        redirect: state.url,
      }
    })
    return false;
  }
};
