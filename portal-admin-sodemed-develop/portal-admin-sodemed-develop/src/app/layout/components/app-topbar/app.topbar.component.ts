import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "../../services/app.layout.service";
import { Router } from '@angular/router';
import { SessionService } from '@core/security/services/session-service.service';
import { SysUser } from '@auth/models/sys-user';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;
    public dataUserInSession: SysUser = null;

    constructor(
        public layoutService: LayoutService,
        public router: Router,
        private sessionService: SessionService
    ) { }

    ngOnInit(): void {
        this.dataUserInSession = this.sessionService.userData
    }

    redirecToProfile() {
        this.router.navigate(['/admin-employee/user-profile/profile']);
    }
}
