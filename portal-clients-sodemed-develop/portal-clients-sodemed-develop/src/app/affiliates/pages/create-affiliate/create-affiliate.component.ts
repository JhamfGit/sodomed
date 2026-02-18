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
import { Beneficiary } from '../../models/beneficiary';
import { TypeIdentificationsExport } from '@shared/models/type-identifications-export';
import { TypesIdentification } from '@shared/models/types-identification';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';
import { KinshipsOptions } from '../../models/kinship-options';
import { Kinship } from '../../models/kinship';
import { BeneficiaryService } from '../../services/beneficiary.service';
import { SessionService } from '@core/security/services/session-service.service';
import { ErrorResponse } from '@core/response/error-response';
import { NumberValidatorDirective } from '@shared/directives/number-validator.directive';
import { CountryCodeNumberDirective } from '@shared/directives/country-code-validator.directive';

@Component({
  selector: 'app-create-affiliate',
  templateUrl: './create-affiliate.component.html',
  styleUrls: ['./create-affiliate.component.scss'],
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
    CountryCodeNumberDirective
  ]
})
export default class CreateAffiliateComponent implements OnInit {
  private _form: FormGroup;

  private typeIdentificationsExport: TypeIdentificationsExport = new TypeIdentificationsExport();
  public typesIds: { label: string, value: TypesIdentification }[];
  private typesKinshipsOptions: KinshipsOptions = new KinshipsOptions()
  public kinshipOptions: { label: string, value: Kinship }[];
  constructor(
    private fb: FormBuilder,
    private notificationService: NotificationService,
    private router: Router,
    private beneficiaryService: BeneficiaryService,
    private sessionService: SessionService,

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
        typeIdentification: ['', Validators.required],
        identification: ['', [Validators.required, Validators.pattern('[0-9]+')]],  // Ejemplo de validación para solo números
        name: ['', [Validators.required, Validators.minLength(3)]],
        lastName: ['', [Validators.required, Validators.minLength(3)]],
        phone: ['', [Validators.required, Validators.pattern('[0-9]{6,15}')]],
        countryDialCode: ['+57', [Validators.required, Validators.pattern('[+][0-9]{1,3}')]],
        email: ['', [Validators.required, Validators.email, Validators.pattern(/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/)]],
        kinship: ['', Validators.required]
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



  cancelSolicitud() {
    this.notificationService.modalBasic('Comfirmar', 'Está seguro que desea cancelar la creación del beneficiario', 'info', () => this.router.navigate(['/']))
  }



  sendRequestCreateBeneficiary() {
    if (!this.form.valid) {
      return
    }
    const userId = `${this.sessionService?.userData?.userId}` ?? null
    const beneficiary = this.formRaw
    this.beneficiaryService.createBeneficiary(userId, beneficiary).subscribe({
      next: (response) => {
        if (response.success) {
          this.notificationService.toastSuccessDefault('Beneficiario registrado', 4000)
          this.notificationService.modalBasic(
            'Realizado con éxito',
            'Beneficiario registrado, a continuación será dirigido al panel de beneficiarios',
            'success',
            () => this.router.navigate(['/admin-client/affiliates/show-affiliates'])
         )
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
      },
    })

  }
}
