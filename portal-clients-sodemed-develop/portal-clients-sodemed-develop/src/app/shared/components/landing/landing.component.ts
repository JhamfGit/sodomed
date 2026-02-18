import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LayoutService } from 'src/app/layout/services/app.layout.service';
import { LandingRoutingModule } from './landing-routing.module';
import { DividerModule } from 'primeng/divider';
import { StyleClassModule } from 'primeng/styleclass';
import { ChartModule } from 'primeng/chart';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';

@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    standalone: true,
    imports :[   
        CommonModule,
        LandingRoutingModule,
        DividerModule,
        StyleClassModule,
        ChartModule,
        PanelModule,
        ButtonModule]
})
export class LandingComponent {

    constructor(public layoutService: LayoutService, public router: Router) { }
    
}
