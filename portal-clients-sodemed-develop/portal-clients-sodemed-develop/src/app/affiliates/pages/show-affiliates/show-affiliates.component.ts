import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { TableModule } from 'primeng/table';
import { Beneficiary, LisBeneficiary } from '../../models/beneficiary';
import { SessionService } from '@core/security/services/session-service.service';
import { BeneficiaryService } from '../../services/beneficiary.service';
import { Router } from '@angular/router';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { TranslateKinshipPipe } from '../../pipes/translate-kinship.pipe';
import { TranslateStatusUserPipe } from '../../pipes/translate-status-user.pipe';
import { SkeletonModule } from 'primeng/skeleton';
import { DialogModule } from 'primeng/dialog';
import { EditBeneficiaryComponent } from '../../components/edit-beneficiary/edit-beneficiary.component';
import { ButtonModule } from 'primeng/button';
@Component({
  selector: 'app-show-affiliates',
  templateUrl: './show-affiliates.component.html',
  styleUrls: ['./show-affiliates.component.scss'],
  standalone: true,
  imports: [
    CommonModule, 
    TableBaseStylesComponent,
    TableModule,
    TranslateKinshipPipe,
    TranslateStatusUserPipe,
    SkeletonModule,
    DialogModule,
    EditBeneficiaryComponent,
    ButtonModule
  ],
  
})
export default class ShowAffiliatesComponent implements OnInit {
  public identificationClient: string = null;
  public arrayBeneficiary: LisBeneficiary[] = [];
  public loadingskeleton : boolean =false
  public currentBeneficiary: Beneficiary;
  public showEditBeneficiary: boolean = false ;
  public loadingUpdateBeneficiary: boolean = false
  constructor(
    private notificationService: NotificationService,
    private router: Router,
    private beneficiaryService: BeneficiaryService,
    private sessionService: SessionService,
  ) { }

  ngOnInit(): void {
    const { identification } = this.sessionService.userData;
    this.processBeneficiarys(identification);
  }


  getBeneficiarys(identificationClient: string) {
    this.loadingskeleton = true;
    this.beneficiaryService.getBeneficiaryByClient(identificationClient).subscribe({
      next: (response) => {
        this.loadingskeleton = false;
        if (response.success) {
          this.arrayBeneficiary = response.data;
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingskeleton = false;
        this.arrayBeneficiary = [];
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  processBeneficiarys(identificationClient: string) {
    if (!identificationClient) {
      return this.notificationService.modalBasic(
        'Por favor, inicia Sesión ',
        'Debe iniciar sesión nuevamente para poder consultar los beneficiarios',
        'info',
        () => {
          this.sessionService.exitSession()
        }
      );
    }

    this.getBeneficiarys(identificationClient)
  }

  editBeneficiary(beneficiary : Beneficiary){
    this.currentBeneficiary = beneficiary
    this.showEditBeneficiary = true; 
    
  }

  saveBeneficiary(beneficiary : Beneficiary){
    this.loadingUpdateBeneficiary = true;
    this.beneficiaryService.updateBeneficiary(beneficiary).subscribe({
      next: (response) => {
        this.loadingUpdateBeneficiary = false;
        if (response.success) {
          this.showEditBeneficiary = false;
          this.notificationService.modalBasic(
            'Realizado con éxito',
            '',
            'success',
            () => {
              const { identification } = this.sessionService.userData;
              this.processBeneficiarys(identification);
            })
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingUpdateBeneficiary = false;
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  

  deleteBeneficiary(beneficiary : Beneficiary) {
    this.loadingskeleton = true;
    this.beneficiaryService.deleteBeneficiary(beneficiary).subscribe({
      next: (response) => {
        this.loadingskeleton = false;
        if (response.success) {
          this.notificationService.modalBasic(
            'Realizado con éxito',
            'Beneficiario eliminado',
            'success',
            () => {
              const { identification } = this.sessionService.userData;
              this.processBeneficiarys(identification);
            })
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingskeleton = false;
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

}
