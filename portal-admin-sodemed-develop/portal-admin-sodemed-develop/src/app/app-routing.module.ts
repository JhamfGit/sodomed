import { ExtraOptions, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app.layout.component';
import { authGuard } from './core/security/guards/auth.guard';

const config: ExtraOptions = { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' }
const routes: Routes = [

    {
        path: '',
        redirectTo: 'admin-employee/dashboard',
        pathMatch: 'full'
    },
    {
        path: 'admin-employee', component: AppLayoutComponent,
        canActivate: [authGuard],
        children: [
            {
                path: '',
                redirectTo: 'dashboard',
                pathMatch: 'full'
            },
            {
                path: 'dashboard',
                loadComponent: () => import('./shared/components/dashboard-home/dashboard-home.component')
            },
            {
                path: 'medication-request',
                loadChildren: () => import('./medical-order-management/medication-request.module').then(m => m.MedicationRequestModule)
            },
            {
                path: 'user-management',
                loadChildren: () => import('./user-management/user-management.module').then(m => m.UserManagementModule)
            },
            {
                path: 'user-profile',
                loadChildren: () => import('./profile/profile.module').then(m => m.ProfileModule)
            },
        ]
    },
    {
        path: 'auth',
        loadChildren: () => import('../app/auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'notfound',
        loadComponent: () => import('./shared/components/notfound/notfound.component')
    },
    {
        path: '**',
        redirectTo: 'notfound'
    },
]

@NgModule({
    imports: [
        RouterModule.forRoot(routes, config)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
