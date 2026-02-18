import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { HttpClient } from '@angular/common/http';
import { SessionService } from '../core/security/services/session-service.service';

@NgModule({
    imports: [
        CommonModule,
        AuthRoutingModule
    ],
    providers: [HttpClient,SessionService]
})
export class AuthModule { }
