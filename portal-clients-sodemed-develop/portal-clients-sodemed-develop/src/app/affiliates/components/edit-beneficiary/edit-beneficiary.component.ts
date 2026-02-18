import { CommonModule } from '@angular/common';
import { Component, Input, type OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';
import { KinshipsOptions } from '../../models/kinship-options';
import { Kinship } from '../../models/kinship';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';
import { BeneficiaryService } from '../../services/beneficiary.service';
import { SessionService } from '@core/security/services/session-service.service';
import { Beneficiary } from '../../models/beneficiary';
import { InputTextModule } from 'primeng/inputtext';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputOtpModule } from 'primeng/inputotp';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { FileUploadModule } from 'primeng/fileupload';
import { FileButtonUploadBasicComponent } from '@shared/components/file-button-upload-basic/file-button-upload-basic.component';
import { NumberValidatorDirective } from '@shared/directives/number-validator.directive';
import { CountryCodeNumberDirective } from '@shared/directives/country-code-validator.directive';
import { EnumStatusGlobal, StatusGlobalType } from '@shared/models/status-global';
import { SelectButtonModule } from 'primeng/selectbutton';

@Component({
    selector: 'app-edit-beneficiary',
    templateUrl: './edit-beneficiary.component.html',
    styleUrl: './edit-beneficiary.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        InputTextModule,
        IconFieldModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
        InputOtpModule,
        DropdownModule,
        FormsModule, ButtonModule,
        InputTextareaModule,
        FileUploadModule,
        FileButtonUploadBasicComponent,
        ReactiveFormsModule,
        NumberValidatorDirective,
        CountryCodeNumberDirective,
        SelectButtonModule
    ],
})
export class EditBeneficiaryComponent implements OnInit {
    @Input() set beneficiary (beneficiary: Beneficiary){
        this._beneficiary = beneficiary;
        this.form.patchValue(this._beneficiary)
        this.form.controls['typeIdentification'].disable()
        this.form.controls['identification'].disable()
    };
    private _beneficiary: Beneficiary;
    private _form: FormGroup;
    private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
    public typesIds: { label: string, value: TypesIdentification }[];
    private typesKinshipsOptions: KinshipsOptions = new KinshipsOptions()
    public kinshipOptions: { label: string, value: Kinship }[];
    public stateOptions: { label: string, value: StatusGlobalType }[] = [
        { label: 'Activado', value: EnumStatusGlobal.Active },
        { label: 'Inactivado', value: EnumStatusGlobal.Inactive },
    ];

    constructor(
        private fb: FormBuilder,
    ) { }

    ngOnInit(): void {
        this.typesIds = this.typeIdentificationsExport.typesIdsReturn
        this.kinshipOptions = this.typesKinshipsOptions.kinshipOptionsReturn
    }


    /**
   * Creación del formulario basado en Beneficiary
   */
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                id: [this.beneficiary.id],
                typeIdentification: [this.beneficiary?.typeIdentification],
                identification: [this.beneficiary?.identification],  // Ejemplo de validación para solo números
                name: [this.beneficiary?.name, [Validators.required, Validators.minLength(3)]],
                lastName: [this.beneficiary?.lastName, [Validators.required, Validators.minLength(3)]],
                phone: [this.beneficiary?.phone, [Validators.required, Validators.pattern('[0-9]{6,15}')]],
                countryDialCode: [this.beneficiary?.countryDialCode, [Validators.required, Validators.pattern('[+][0-9]{1,3}')]],
                email: [this.beneficiary?.email, [Validators.required, Validators.email, Validators.pattern(/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/)]],
                kinship: [this.beneficiary?.kinship, Validators.required],
                status: [this.beneficiary?.status, Validators.required]
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): Beneficiary {
        return this.form.getRawValue();
    }

    get beneficiary() : Beneficiary{
        return this._beneficiary
    } 
}
