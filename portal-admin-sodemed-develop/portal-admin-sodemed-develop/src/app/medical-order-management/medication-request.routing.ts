import { Routes, RouterModule } from '@angular/router';
import { MedicationRequestManagementComponent } from './pages/medication-request-management/medication-request-management.component';

const routes: Routes = [
  {
    path: 'management',
    loadComponent: () => MedicationRequestManagementComponent
  }
];

export const MedicationRequestRoutes = RouterModule.forChild(routes);
