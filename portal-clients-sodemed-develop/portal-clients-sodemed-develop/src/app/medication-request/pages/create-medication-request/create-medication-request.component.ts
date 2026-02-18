import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { InputOtpModule } from 'primeng/inputotp';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { DropdownModule } from 'primeng/dropdown';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { FileUploadModule } from 'primeng/fileupload';
import { FileButtonUploadBasicComponent } from '@shared/components/file-button-upload-basic/file-button-upload-basic.component';
import { EnumUploadDoc, TypeUploadDoc } from '../../models/type-upload-doc';
import { Router } from '@angular/router';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { BeneficiaryService } from '@medication-request/services/beneficiary.service';
import { ErrorResponse } from '@core/response/error-response';
import { GrupAffiliateDropdown } from '@medication-request/models/grup-affiliate-dropdown';
import { SysUser } from '@auth/models/sys-user';
import { MedicalRequestService } from '@medication-request/services/medical-request.service';
import { EnumTypeClient } from '@core/models/type-client';
import { LisBeneficiary } from 'src/app/affiliates/models/beneficiary';

@Component({
  selector: 'app-create-medication-request',
  templateUrl: './create-medication-request.component.html',
  styleUrls: ['./create-medication-request.component.scss'],
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
    ReactiveFormsModule
  ],
})
export default class CreateMedicationRequestComponent implements OnInit {
  public enumUploadDoc = EnumUploadDoc;
  public optionTypeAffiliate: GrupAffiliateDropdown[] = [];
  private _form: FormGroup;
  public fileUploaDocIndentity: any;
  public fileUploaDocMedicalOrder: any;
  public fileUploaDocClinicalHistory: any;
  public loadingAfiliate: boolean = false;
  public dataUserInSession: SysUser = null;
  public fileAccept = '.pdf,.jpg,.jpeg,.png,.webp'
  public maxFileSize: number = 2097152
  public loadingCharge: boolean = false;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private SessionService: SessionService,
    private notificationService: NotificationService,
    private beneficiaryService: BeneficiaryService,
    private medicalRequestService: MedicalRequestService
  ) { }


  ngOnInit(): void {
    this.dataUserInSession = this.SessionService.userData
    this.getBeneficiarys(this.dataUserInSession)
  }

  /**
   * crecion del formulario
  */
  get form(): FormGroup {
    if (this._form == null) {
      this._form = this.fb.group({
        whoAffiliate: ['', [Validators.required]],
        homeCity: ['', Validators.required],
        homeAddress: ['', Validators.required],
        district: ['', Validators.required],
        furtherDirectionIndication: ['', Validators.required],
        comments: ['', Validators.required]
      })
    }
    return this._form;
  }

  /**
   * devuelve el valor del formulario
   */
  get formRaw() {
    return this.form.getRawValue();
  }


  uploadDoc(fileUpload, typeUploadDoc: TypeUploadDoc) {
    switch (typeUploadDoc) {
      case this.enumUploadDoc.docClinicalHistory:
        this.fileUploaDocClinicalHistory = fileUpload
        break;
      case this.enumUploadDoc.docIndentity:
        this.fileUploaDocIndentity = fileUpload
        break;
      case this.enumUploadDoc.docMedicalOrder:
        this.fileUploaDocMedicalOrder = fileUpload
        break;
      default:
        console.info("SELETEC TYPE UPLOAD FILE")
        break;
    }
  }


  cancelSolicitud() {
    this.notificationService.modalConfirmAction({
      title: 'Confirmar',
      text: 'Está seguro que desea cancelar la creación de esta solicitud médica',
      icon: 'info',
      onConfirm: () => this.router.navigate(['/admin-client'])
    })
  }
  get isfiles() {
    return this.fileUploaDocClinicalHistory && this.fileUploaDocIndentity && this.fileUploaDocMedicalOrder
  }

  sendRequest() {
    if (!this.form.valid) {
      return
    }
    this.loadingCharge = true;
    const userCreate = this.dataUserInSession?.identification
    const buildData = {
      creationDate: new Date(),
      userCreate: userCreate,
      userOrderIdentification: this.formRaw.whoAffiliate.identification,
      userOrderName: this.formRaw.whoAffiliate.userName,
      typeClient: this.formRaw.whoAffiliate.userType,
      homeCity: this.formRaw.homeCity,
      homeAddress: this.formRaw.homeAddress,
      district: this.formRaw.district,
      furtherDirectionIndication: this.formRaw.furtherDirectionIndication,
      comments: this.formRaw.comments,
    }
    this.medicalRequestService.sendMedicalRequest(
      this.fileUploaDocIndentity,
      this.fileUploaDocMedicalOrder,
      this.fileUploaDocClinicalHistory,
      buildData
    ).subscribe({
      next: (response) => {
        this.loadingCharge = false;
        if (response.success) {
          this.notificationService.modalBasic(
            'Realizado con éxito',
            'La Solicitud médica se ha enviado correctamente, a continuación será dirigido al historial de solicitudes donde podrá ver el estado de su orden',
            'success',
            () => this.router.navigate(['/admin-client/medication-request/delete'])
          )
        }

      },
      error: (error) => {
        this.loadingCharge = false;
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
      },
    })

  }
  getBeneficiarys(dataUserInSession: SysUser) {
    this.loadingAfiliate = true;
    this.beneficiaryService.getBeneficiaryByClient(dataUserInSession?.identification).subscribe({
      next: (response) => {
        this.loadingAfiliate = false;
        if (response.success) {
          if (response.success) {
            this.optionTypeAffiliate = this.createOptionTypeAffiliate(dataUserInSession, response.data);
          }
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingAfiliate = false;
        this.optionTypeAffiliate = [];
        this.notificationService.toasErrorI18n(err)
      },
    })
  }


  private createOptionTypeAffiliate(dataUserInSession: SysUser, beneficiaries: LisBeneficiary[]) {
    return [
      {
        label: 'Cotizante',
        value: 'Cotizante',
        items: [
          {
            label: `${dataUserInSession?.name} ${dataUserInSession?.lastName}`,
            value: {
              identification: dataUserInSession.identification,
              userType: EnumTypeClient.Contizing,
              userName: `${dataUserInSession?.name} ${dataUserInSession?.lastName}`,
            },
          },
        ],
      },
      {
        label: 'Beneficiario',
        value: 'Beneficiario',
        items: beneficiaries.map((item) => ({
          label: `${item.name} ${item.lastName}`,
          value: {
            identification: item.identification,
            userType: EnumTypeClient.Beneficiary,
            userName: `${item.name} ${item.lastName}`,
          },
        })),
      },
    ].filter(user => user.items.length > 0);
  }

  errorUpload($event) {

    this.notificationService.toasGeneric('Error al cargar Archivo', $event, 'error')
  }
}
