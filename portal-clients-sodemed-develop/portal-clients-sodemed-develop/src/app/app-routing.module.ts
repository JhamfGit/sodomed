import { ExtraOptions, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app.layout.component';
import { authGuard } from './core/security/guards/auth.guard';

const config: ExtraOptions = { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' }
const routes: Routes = [

    {
        path: '',
        redirectTo: 'admin-client/dashboard',
        pathMatch: 'full'
    },
    {
        path: 'admin-client', component: AppLayoutComponent,
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
            }
            ,
            {
                path: 'medication-request',
                loadChildren: () => import('./medication-request/medication-request.module').then(m => m.MedicationRequestModule)
            },
            {
                path: 'affiliates',
                loadChildren: () => import('./affiliates/affiliates.module').then(m => m.AffiliatesModule)
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
