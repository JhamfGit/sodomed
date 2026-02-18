import { Routes, RouterModule } from '@angular/router';
import CreateMedicationRequestComponent from './pages/create-medication-request/create-medication-request.component';
import DeleteMedicationRequestComponent from './pages/delete-medication-request/delete-medication-request.component';
import { HistoryMedicationRequestComponent } from './pages/history-medication-request/history-medication-request.component';

const routes: Routes = [
  {
    path: 'create',
    loadComponent: () => CreateMedicationRequestComponent
  },
  {
    path: 'delete',
    loadComponent: () => DeleteMedicationRequestComponent
  },
  {
    path: 'history',
    loadComponent: () => HistoryMedicationRequestComponent
  }
];

export const MedicationRequestRoutes = RouterModule.forChild(routes);
