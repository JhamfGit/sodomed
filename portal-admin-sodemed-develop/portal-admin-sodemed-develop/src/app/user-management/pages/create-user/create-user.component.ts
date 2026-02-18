import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FileButtonUploadBasicComponent } from '@shared/components/file-button-upload-basic/file-button-upload-basic.component';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FileUploadModule } from 'primeng/fileupload';
import { IconFieldModule } from 'primeng/iconfield';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputIconModule } from 'primeng/inputicon';
import { InputOtpModule } from 'primeng/inputotp';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';
import { NumberValidatorDirective } from '@shared/directives/number-validator.directive';
import { CountryCodeNumberDirective } from '@shared/directives/country-code-validator.directive';
import { CheckboxModule } from 'primeng/checkbox';
import { PasswordModule } from 'primeng/password';
import { EnumUserType } from '@auth/models/sys-user';
import { UserEmployee } from '@auth/models/register-user';
import { UserManagementService } from '../../services/user-management.service';
import { RoleUserService } from '../../services/role-user.service';

@Component({
    selector: 'app-create-affiliate',
    templateUrl: './create-user.component.html',
    styleUrls: ['./create-user.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        InputTextModule,
        IconFieldModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
        DropdownModule,
        FormsModule, ButtonModule,
        InputTextareaModule,
        FileUploadModule,
        FileButtonUploadBasicComponent,
        ReactiveFormsModule,
        NumberValidatorDirective,
        CountryCodeNumberDirective,

        CheckboxModule,
        PasswordModule,
    ]
})
export default class CreateUserComponent implements OnInit {
    private _form: FormGroup;
    private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
    public typesIds: { label: string, value: TypesIdentification }[];
    public loadingCreateUser: boolean = false;
    public loadingRole : boolean = false
    public roles: {label:string, value: number}[] = []
    constructor(
        private fb: FormBuilder,
        private notificationService: NotificationService,
        private router: Router,
        private userManagementService: UserManagementService,
        private roleUserService: RoleUserService
    ) { }

    ngOnInit(): void {
        this.typesIds = this.typeIdentificationsExport.typeIdsEmployee;
        this.getRoles();
    }


    /**
  * Creación del formulario basado en UserEmployee
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
                userType: [EnumUserType.Employee],
                roleId: ['',Validators.required],
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): UserEmployee {
        return this.form.getRawValue();
    }



    cancelSolicitud() {
        this.notificationService.modalConfirmAction({
            title: 'Confirmar',
            text: 'Está seguro que desea cancelar la creación de usuario',
            icon: 'info',
            onConfirm: () => this.router.navigate(['/'])
        })
    }



    sendRequestCreateUserEmployee() {
        if (!this.form.valid) {
            return
        }
        this.loadingCreateUser = true;
        this.userManagementService.createUserEmployye(this.formRaw).subscribe({
            next: (response) => {
                if (response.success) {
                }
                this.loadingCreateUser = false
                this.showConfirmationModal(this.formRaw.name)
            },
            error: (err) => {
                console.log('ERROR', err)
                this.loadingCreateUser = false
                this.notificationService.toasErrorI18n(err)
            },
        })
    }

    private showConfirmationModal(name: string) {
        this.notificationService.modalConfirmAction({
            title: `Registro de ${name} realizado`,
            text: `A continuación será redirigido a la pantalla de usuarios.`,
            icon: 'success',
            allowEscapeKey: false,
            allowOutsideClick: false,
            onConfirm: () => this.router.navigate(['/admin-employee/user-management/show-user-management']),
            showCancelButton: false,
        });
    }


    getRoles() {
        this.loadingRole = true;
        this.roleUserService.getRolesByStatus().subscribe({
            next: (response) => {
                this.loadingRole = false
                if (response.success) {
                    this.roles = response?.data.map(role => ({label: role.name, value :role.id}))
                }
            },
            error: (err) => {
                console.log('ERROR', err)
                this.loadingRole = false
                this.notificationService.toasErrorI18n(err)
            },
        })
    }


}
