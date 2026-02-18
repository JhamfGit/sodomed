import { NgModule } from '@angular/core';
import { DatePipe, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppLayoutModule } from './layout/app.layout.module';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { AuthInterceptor } from './core/security/interceptor/auth.interceptor';
import { ErrorHandlerInterceptor } from '@core/security/interceptor/error-handler.interceptor';
@NgModule({
    declarations: [AppComponent],
    imports: [
        AppLayoutModule,
        AppRoutingModule,
        BrowserModule,
        BrowserAnimationsModule,

    ],
    providers: [
        provideHttpClient(withInterceptors([AuthInterceptor, ErrorHandlerInterceptor])),
        { provide: LocationStrategy, useClass: PathLocationStrategy },
        DatePipe
    ],
    bootstrap: [AppComponent],
})
export class AppModule { }
