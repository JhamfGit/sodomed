import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from '../../services/app.layout.service';
import { MenuItem } from 'primeng/api';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: MenuItem[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: [''] }
                ]
            },
       
            {
                label: 'Solicitudes Medicas',
                icon: 'pi pi-fw pi-user',
                items: [
                    {
                        label: 'Crear solicitud',
                        icon: 'pi pi-fw pi-book',
                        routerLink: ['medication-request/create']
                    },
                    {
                        label: 'Eliminar solicitud',
                        icon: 'pi pi-fw pi-delete-left',
                        routerLink: ['medication-request/delete']
                    },
                    {
                        label: 'Historial solicitudes',
                        icon: 'pi pi-fw pi-history',
                        routerLink: ['medication-request/history']
                    }
                ]
            },
            {
                label: 'Beneficiario',
                icon: 'pi pi-fw pi-user',
                items: [
                    {
                        label: 'Ver Beneficiarios',
                        icon: 'pi pi-fw pi-users',
                        routerLink: ['affiliates/show-affiliates']
                    },
                    {
                        label: 'Registrar Beneficiario',
                        icon: 'pi pi-fw pi-user-plus',
                        routerLink: ['affiliates/create']
                    }
                ]
            },
        ];
    }
}
