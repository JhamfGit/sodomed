import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from '../../services/app.layout.service';
import { MenuItem } from 'primeng/api';
import { MenuService } from '../../services/app.menu.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: MenuItem[] = [];

    constructor(
        public layoutService: LayoutService,
        public menuService: MenuService

    ) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }
                ],
                perms: []
            },

            {
                label: 'Operaciones',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: 'Gestión de solicitudes médicas',
                        icon: 'pi pi-fw pi-clipboard',
                        routerLink: ['medication-request/management'],
                        perms: ['admin:sodemed', 'medications:sodemed']
                    },
                    {
                        label: 'Administración de usuarios',
                        icon: 'pi pi-fw pi-users',
                        perms: ['admin:sodemed', 'employees:sodemed'],
                        items: [
                            {
                                label: 'Crear usuario',
                                icon: 'pi pi-fw pi-user-plus',
                                routerLink: ['user-management/create'],
                                perms: [],
                            },

                            {
                                label: 'Ver usuarios',
                                icon: 'pi pi-fw pi-user',
                                routerLink: ['user-management/show-user-management'],
                                perms: [],
                            }
                        ]
                    },
                    {
                        label: 'Administración de roles',
                        icon: 'pi pi-fw pi-unlock',
                        routerLink: ['user-management/role-management'],
                        perms: ['admin:sodemed', 'roles:sodemed']


                    },
                ],

            },
        ];

        this.model = this.menuService.menuBuilder(this.model);
    }
}
