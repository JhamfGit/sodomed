import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SysUser } from '@auth/models/sys-user';
import { ErrorResponse } from '@core/response/error-response';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { ResponseOrders } from '@medication-request/models/response-orders';
import { DictionaryStatusOrderPipe } from '@medication-request/pipes/dictionary-status-order.pipe';
import { MedicalRequestService } from '@medication-request/services/medical-request.service';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { StatusOrder } from '@shared/models/status-order';
import { GetStylesStatusOrderPipe } from '@shared/pipes/get-styles-status-order.pipe';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-delete-medication-request',
  templateUrl: './delete-medication-request.component.html',
  styleUrls: ['./delete-medication-request.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    TableBaseStylesComponent,
    TableModule,
    DictionaryStatusOrderPipe,
    GetStylesStatusOrderPipe,
    ButtonModule
  ],
})
export default class DeleteMedicationRequestComponent implements OnInit {
  public dataUserInSession: SysUser;
  public reponseOrderToDelete: ResponseOrders[] = []
  public loadingDelete: boolean = false;
  constructor(
    private sessionService: SessionService,
    private notificationService: NotificationService,
    private router: Router,
    private medicalRequestService: MedicalRequestService
  ) { }

  ngOnInit(): void {
    this.dataUserInSession = this.sessionService?.userData;
    this.getOrdesToDelete(this.dataUserInSession?.userId)
  }


  getOrdesToDelete(idUserCreate: number) {
    this.medicalRequestService.getOrdesByIdUserCreateAndStatusOrdes(idUserCreate, StatusOrder.Pending).subscribe({
      next: (response) => {
        if (response.success) {
          this.reponseOrderToDelete = response.data
        }
      },
      error: (error) => {
        this.notificationService.toasErrorI18n(error)
      },
    })
  }

  deleteMedicalRequest(order: ResponseOrders) {
    this.loadingDelete = true;
    this.medicalRequestService.deleteMedicalRequest(order.id).subscribe({
      next: (response) => {
        this.loadingDelete = false;
        if (response.success) {
          this.getOrdesToDelete(this.dataUserInSession?.userId)

        }
      },
      error: (error) => {
        this.loadingDelete = false;
        this.notificationService.toasErrorI18n(error)
      },
    })

  }

  alertMsgMedicalRequest(order: ResponseOrders) {
    this.notificationService.modalConfirmAction({
      title: 'Eliminar Solicitud',
      text: 'Está seguro que desea eliminar la solicitud médica',
      icon: 'info',
      onConfirm: () => this.deleteMedicalRequest(order)
    })
  }
}
