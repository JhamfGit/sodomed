import { Routes, RouterModule } from '@angular/router';
import CreateAffiliateComponent from './pages/create-affiliate/create-affiliate.component';
import ShowAffiliatesComponent from './pages/show-affiliates/show-affiliates.component';

const routes: Routes = [
  {
    path: 'create',
    loadComponent: () => CreateAffiliateComponent
  },
  {
    path: 'show-affiliates',
    loadComponent: () => ShowAffiliatesComponent
  },
];

export const AffiliatesRoutes = RouterModule.forChild(routes);
