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
    selector: 'app-create-role',
    templateUrl: './create-rol.component.html',
    styleUrl: './create-rol.component.scss',
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
export class CreateRolComponent {
    @Input() arrayPermissions: string[] = []
    @Input() ListRole: string[] = [];
    public stateOptions: { label: string, value: StatusRoleType }[] = [
        { label: 'Activado', value: EnumStatusRole.Active },
        { label: 'Inactivado', value: EnumStatusRole.Inactive },
    ];
    private _form: FormGroup;
    public targetPermissions: string[] = [];
    constructor(
        private fb: FormBuilder,
    ) { }

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
                code: ['', [Validators.required]],
                status: [EnumStatusRole.Active, [Validators.required]],
                permissions: [this.targetPermissions,Validators.required],
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): RoleEmployee {
        return this.form.getRawValue();
    }

    get validForm() {
        return this.form.valid
    }


    onMoveToTarget() {
        const currentValue = this.targetPermissions?.length > 0 ? this.targetPermissions : [];
        this.form.controls['permissions'].setValue(currentValue)
    }

    onMoveToSource() {
        const currentValue = this.targetPermissions?.length > 0 ? this.targetPermissions : [];
        this.form.controls['permissions'].setValue(currentValue)
    }
}
