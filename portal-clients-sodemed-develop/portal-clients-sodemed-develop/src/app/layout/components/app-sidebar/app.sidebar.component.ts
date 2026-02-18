import { Component, ElementRef } from '@angular/core';
import { LayoutService } from "../../services/app.layout.service";
import { SessionService } from '@core/security/services/session-service.service';

@Component({
    selector: 'app-sidebar',
    templateUrl: './app.sidebar.component.html'
})
export class AppSidebarComponent {
    constructor(public layoutService: LayoutService, public el: ElementRef, private sessionService : SessionService) { }

    logout() {
        this.sessionService.exitSession();
    }
}

