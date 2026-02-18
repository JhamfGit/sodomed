import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { NumberValidatorDirective } from '@shared/directives/number-validator.directive';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { IconFieldModule } from 'primeng/iconfield';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputIconModule } from 'primeng/inputicon';
import { InputOtpModule } from 'primeng/inputotp';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { EnumStatusRole, RoleEmployee, StatusRoleType } from '../../models/role';
import { InputSwitch, InputSwitchModule } from 'primeng/inputswitch';
import { DialogModule } from 'primeng/dialog';
import { SelectButtonModule } from 'primeng/selectbutton';
import { PickListModule } from 'primeng/picklist';
@Component({
    selector: 'app-edit-role',
    templateUrl: './edit-role.component.html',
    styleUrl: './edit-role.component.scss',
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
        ReactiveFormsModule,
        NumberValidatorDirective,
        SelectButtonModule,
        PickListModule

    ],
})
export class EditRoleComponent implements OnInit, OnChanges {
    @Input({ required: true }) role: RoleEmployee
    @Input() arrayPermissions: string[] = [];
    public stateOptions: { label: string, value: StatusRoleType }[] = [
        { label: 'Activado', value: EnumStatusRole.Active },
        { label: 'Inactivado', value: EnumStatusRole.Inactive },
    ];
    private _form: FormGroup;
    public sourcePermissions: string[] = [];
    public targetPermissions: string[] = [];
    constructor(
        private fb: FormBuilder,
    ) { }

    ngOnChanges(changes: SimpleChanges): void {
        const currentRole = changes['role']
        if (currentRole) {
            this.form.patchValue(currentRole.currentValue)
        }
    }
    ngOnInit(): void {
    }

    /**
    * Creación del formulario basado en Beneficiary
    */
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                name: ['', [Validators.required]],
                description: ['', [Validators.required]],
                status: ['', [Validators.required]],
                permissions: [[], [Validators.required]],
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): RoleEmployee {
        return {
            ...this.form.getRawValue(),
            id: this.role.id,
            code: this.role.code,
        };
    }

    get validForm() {
        return this.form.valid
    }
}
