import { Routes } from '@angular/router';

import { authGuard } from './core/guards/auth.guard';
import { guestGuard } from './core/guards/guest.guard';

export const routes: Routes = [
  {
    path: 'login',
    canActivate: [guestGuard],
    loadComponent: () =>
      import('./features/auth/login/login.component').then((m) => m.LoginComponent)
  },
  {
    path: 'students',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./features/students/student-list/student-list.component').then(
        (m) => m.StudentListComponent
      )
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'students'
  },
  {
    path: '**',
    redirectTo: 'students'
  }
];
