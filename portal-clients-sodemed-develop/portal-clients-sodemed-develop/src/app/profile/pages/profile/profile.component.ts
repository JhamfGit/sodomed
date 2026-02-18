import { CommonModule } from '@angular/common';
import { Component, type OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRoutingModule } from '@auth/login/login-routing.module';
import { RegisterUserClient } from '@auth/models/register-user-client';
import { EnumUserType, SysUser } from '@auth/models/sys-user';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { CountryCodeNumberDirective } from '@shared/directives/country-code-validator.directive';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ProfileService } from '../../services/profile.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        LoginRoutingModule,
        ButtonModule,
        CheckboxModule,
        InputTextModule,
        FormsModule,
        PasswordModule,
        ReactiveFormsModule,
        DropdownModule,
        CountryCodeNumberDirective,
        DialogModule
    ],
})
export default class ProfileComponent implements OnInit {

    private _form: FormGroup;
    private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
    public typesIds: { label: string, value: TypesIdentification }[];
    public loadingLogin: boolean = false;
    public dataUserInSession: SysUser = null;
    public showChangePassword: boolean = false;
    public loadingUpdate: boolean = false;
    constructor(
        private fb: FormBuilder,
        private notificationService: NotificationService,
        private sessionService: SessionService,
        private profileService: ProfileService,
        private router: Router

    ) { }

    ngOnInit(): void {
        this.dataUserInSession = this.sessionService.userData
        this.typesIds = this.typeIdentificationsExport.typesIdsReturn
        this.setDataUser();

    }

    /**
* Creación del formulario basado en Beneficiary
*/
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                typeIdentification: ['', Validators.required],
                identification: ['', [Validators.required, Validators.pattern('[0-9]+')]],  // Ejemplo de validación para solo números
                name: ['', [Validators.required, Validators.minLength(3)]],
                lastName: ['', [Validators.required, Validators.minLength(3)]],
                phone: ['', [Validators.required, Validators.pattern('[0-9]{6,15}')]],
                countryDialCode: ['+57', [Validators.required, Validators.pattern('[+][0-9]{1,3}')]],
                email: ['', [Validators.required, Validators.email, Validators.pattern(/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/)]],
                password: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&()_+*\-=\[\]{};':"\\|,.<>\/?]).{8,}$/)]],
                userType: [EnumUserType.Client],
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): RegisterUserClient {
        return this.form.getRawValue();
    }
    cancel() {
        this.router.navigate(['/']);
    }


    save() {
        this.loadingUpdate = true;
        this.profileService.update(this.formRaw, this.dataUserInSession.userId).subscribe({
            next: (response) => {
                this.loadingUpdate = false;
                if (response.success) {
                    this.showChangePassword = false;
                    this.sessionService.updateDataSysUser(this.dataUserInSession,
                        {
                            email: response.data.email,
                            name: response.data.name,
                            lastName: response.data.lastName,
                            phone: response.data.phone,
                            countryDialCode: response.data.countryDialCode as `+${string}`
                        })
                    this.notificationService.modalBasic(
                        'Realizado con éxito',
                        '',
                        'success'
                    )
                }
            },
            error: (err) => {
                console.log('ERROR', err)
                this.loadingUpdate = false;
                this.notificationService.toasErrorI18n(err)
            },
        })

    }

    setDataUser() {
        if (!this.dataUserInSession) {
            return this.notificationService.modalBasic(
                'Por favor, inicia sesión ',
                'Debe iniciar sesión nuevamente',
                'info',
                () => {
                    this.sessionService.exitSession()
                }
            );
        }

        this.form.patchValue(this.dataUserInSession)
        this.form.controls['typeIdentification'].disable()
        this.form.controls['identification'].disable()
        this.form.controls['email'].disable()
    }



}
