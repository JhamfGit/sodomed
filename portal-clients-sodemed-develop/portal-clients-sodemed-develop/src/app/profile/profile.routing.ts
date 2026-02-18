import { Routes, RouterModule } from '@angular/router';
import ProfileComponent from './pages/profile/profile.component';

const routes: Routes = [
  {
    path: 'profile',
    loadComponent: () => ProfileComponent
  }
];

export const ProfileRoutes = RouterModule.forChild(routes);
