import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { MenuChangeEvent } from '../models/menuchangeevent';
import { MenuItem } from 'primeng/api';
import { SessionService } from '@core/security/services/session-service.service';

@Injectable({
    providedIn: 'root'
})
export class MenuService {

    private menuSource = new Subject<MenuChangeEvent>();
    private resetSource = new Subject();

    menuSource$ = this.menuSource.asObservable();
    resetSource$ = this.resetSource.asObservable();

    constructor(private sessionService: SessionService) { }
    onMenuStateChange(event: MenuChangeEvent) {
        this.menuSource.next(event);
    }

    reset() {
        this.resetSource.next(true);
    }


    menuBuilder(menuItems: MenuItem[]): MenuItem[] {
        return menuItems
            .filter(item => {
                // Validar si el ítem tiene permisos definidos
                const hasPermission = item['perms'] && item['perms'].length > 0
                    ? this.sessionService.hasAnyPermission(item['perms']).have
                    : true; // Si `perms` está vacío o no existe, mostrar el ítem.
                // Si no tiene permisos para el ítem padre, eliminarlo
                if (!hasPermission) {
                    return false;
                }
                // Si el ítem tiene submenús (items), construirlos recursivamente
                if (item.items) {
                    item.items = this.menuBuilder(item.items);
                }
                // Incluir el ítem si pasa las validaciones
                return true;
            });
    }


}
