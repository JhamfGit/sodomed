import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { LoginRoutingModule } from '../login/login-routing.module';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Password, PasswordModule } from 'primeng/password';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';
import { ErrorResponse } from '@core/response/error-response';
import { DropdownModule } from 'primeng/dropdown';
import { RegisterServiceService } from '../services/register-service.service';
import { EnumUserType, RegisterUserClient } from '../models/register-user-client';
import { CountryCodeNumberDirective } from '@shared/directives/country-code-validator.directive';

@Component({
    selector: 'app-register-client',
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
        CountryCodeNumberDirective
    ],
    templateUrl: `./register-client.component.html`,
    styleUrl: './register-client.component.scss',
})
export default class RegisterClientComponent {
    private _form: FormGroup;
    private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
    public typesIds: { label: string, value: TypesIdentification }[];
    public loadingLogin: boolean = false;
    constructor(
        private fb: FormBuilder,
        private notificationService: NotificationService,
        private router: Router,
        private registerServiceService: RegisterServiceService,
    ) { }

    ngOnInit(): void {
        this.typesIds = this.typeIdentificationsExport.typesIdsReturn
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



    cancelSolicitud() {
        this.notificationService.modalConfirmAction({
            title: 'Regresar al inicio de sesión',
            text: 'Estas seguro que deseas cancelar el registro',
            icon: 'info',
            onConfirm: () => this.router.navigate(['/auth/login'])
        })
    }



    sendRequestCreateBeneficiary() {
        if (!this.form.valid) {
            return
        }
        this.loadingLogin = true;
        this.registerServiceService.registerUserClient(this.formRaw).subscribe({
            next: (response) => {
                if (response.success) {
                    this.showConfirmationModal(this.formRaw?.name)
                }
                this.loadingLogin = false
            },
            error: (err) => {
                console.log('error', err)
                this.loadingLogin = false
                this.notificationService.toasErrorI18n(err)
            },
        })

    }

    private showConfirmationModal(name: string) {
        this.notificationService.modalConfirmAction({
            title: `Registro de ${name} realizado`,
            text: `Ahora serás dirigido a la pantalla de inicio de sesión.`,
            icon: 'success',
            allowEscapeKey : false,
            allowOutsideClick: false,
            onConfirm: () => this.router.navigate(['/auth/login']).then(() => {
                this.notificationService.toastSuccessDefault(`Registro de ${name} realizado, por favor inicie sesión`, 6000);
            }),
            showCancelButton: false,
        });
    }


}
