import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  let pass = false;
  authService.getCurrentUser().subscribe((user) => {
    pass = !!user;
    if (!pass) {
      router.navigate(['/login']);
    }
    return pass;
  });
  return pass;
};
