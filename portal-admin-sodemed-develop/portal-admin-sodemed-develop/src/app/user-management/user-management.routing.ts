import { Routes, RouterModule } from '@angular/router';
import CreateUserComponent from './pages/create-user/create-user.component';
import ShowUsersComponent from './pages/show-user/show-user.component';
import RoleManagementComponent from './pages/role-management/role-management.component';

const routes: Routes = [
  {
    path: 'create',
    loadComponent: () => CreateUserComponent
  },
  {
    path: 'show-user-management',
    loadComponent: () => ShowUsersComponent
  },
  {
    path: 'role-management',
    loadComponent: () => RoleManagementComponent
  },
];

export const UserManagementRoutes = RouterModule.forChild(routes);
