import { Component } from '@angular/core';
import { LayoutService } from 'src/app/layout/services/app.layout.service';
import { LoginServiceService } from '../services/login-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../../core/security/services/session-service.service';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/core/services/alert/sweet-alert.service';
import { ErrorResponse } from 'src/app/core/response/error-response';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .pi-eye,
        :host ::ng-deep .pi-eye-slash {
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `]
})
export class LoginComponent {

    private _form: FormGroup;
    public loadingLogin: boolean = false;
    public showRecoveryPassword: boolean;
    public loadingRecoveryPassword: boolean = false;
    private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
    public typesIds: { label: string, value: TypesIdentification }[];
    constructor(
        private loginServiceService: LoginServiceService, private fb: FormBuilder,
        private router: Router,
        private SessionService: SessionService,
        private notificationService: NotificationService
    ) { }

    ngOnInit(): void {
        this.typesIds = this.typeIdentificationsExport.typesIdsReturnOnlyToLogin
    }
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                identification: ['', [Validators.required, Validators.minLength(5)]],
                password: ['', Validators.required],
                typeIdentification: ['CC', Validators.required]
            })
        }
        return this._form;
    }

    get formRaw(): { identification: string, password: string, typeIdentification: TypesIdentification} {
        return this.form.getRawValue()
    }

    sendLogin() {
        if (!this.form.valid) {
            return
        }
        this.loadingLogin = true;
        this.loginServiceService.userLogin(this.formRaw.identification, this.formRaw.password, this.formRaw.typeIdentification).subscribe({
            next: (response) => {
                if (response.success) {
                    const isLoggin: boolean = this.SessionService.setDataLocalStorage(response.data);
                    if (isLoggin) {
                        this.router.navigate(['/admin-employee']).then(() => this.notificationService.toastSuccessDefault(
                            `Bienvenido ${response?.data.name} ${response?.data.lastName}`,
                            4000
                        ));
                    }
                }
                this.loadingLogin = false;
            },
            error: (err) => {
                console.log('🚀 ~ err:', err)
                this.loadingLogin = false;
                this.notificationService.toasErrorI18n(err)
            },
        })

    }

    initProcessRecoreryPassword() {
        this.showRecoveryPassword = true;
    }

    onRequestChange(data: { identification: string }) {
        this.loadingRecoveryPassword = true;
        this.loginServiceService.requestChangePassword(data.identification).subscribe({
            next: (response) => {
                if (response.success) {
                    this.loadingRecoveryPassword = false;
                    this.showRecoveryPassword = false;
                    const { email } = response.data
                    this.notificationService.modalBasic(
                        'Realizado con éxito',
                        `Se ha enviado un enlace de restablecimiento de contraseña a su correo electrónico. ${email ?? ''}
                         Por favor, revise su bandeja de entrada y siga las instrucciones para completar el proceso.
                         Si no encuentra el correo, verifique su carpeta de spam o correo no deseado.`,
                        'success',
                    )
                }
            },
            error: (err) => {
                console.log('🚀 ~ err:', err)
                this.loadingRecoveryPassword = false;
                this.notificationService.toasErrorI18n(err)
            },
        })
    }
}
