import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { Router } from '@angular/router';
import { SysUser } from '@auth/models/sys-user';
import { ResponseOrders } from '@medication-request/models/response-orders';
import { MedicalRequestService } from '@medication-request/services/medical-request.service';
import { DictionaryStatusOrderPipe } from '@medication-request/pipes/dictionary-status-order.pipe';
import { GetStylesStatusOrderPipe } from '@shared/pipes/get-styles-status-order.pipe';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { BadgeModule } from 'primeng/badge';
import { TagModule } from 'primeng/tag';
import { DividerModule } from 'primeng/divider';
import { StatusOrder } from '@shared/models/status-order';
@Component({
  selector: 'app-history-medication-request',
  templateUrl: './history-medication-request.component.html',
  styleUrls: ['./history-medication-request.component.scss'],
  standalone: true,
  imports:[
    CommonModule, 
    TableBaseStylesComponent,
    TableModule,
    DictionaryStatusOrderPipe,
    GetStylesStatusOrderPipe,
    ButtonModule,
    DialogModule,
    BadgeModule,
    TagModule,
    DividerModule
  ],
})
export class HistoryMedicationRequestComponent implements OnInit {
  public dataUserInSession : SysUser;
  public reponseOrder : ResponseOrders[] =[]
  public showObservation:boolean = false;
  public observationOrder: string= null;
  public ordenDetails: ResponseOrders= null;
  public statusOrder= StatusOrder
  constructor(
    private sessionService: SessionService,
    private notificationService: NotificationService,
    private router: Router,
    private medicalRequestService: MedicalRequestService
  ) { }

  ngOnInit(): void {
    this.dataUserInSession =   this.sessionService?.userData;
    this.getOrdersHistory(this.dataUserInSession?.userId)
  }


  getOrdersHistory(idUserCreate: number) {
    this.medicalRequestService.getOrdesHistoryByIdUser(idUserCreate).subscribe({
      next: (response) => {
        if (response.success) {
          this.reponseOrder=  response.data
        }
      },
      error: (error) => {
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
      },
    })
  }

  showDetail(ordenDetails: ResponseOrders= null){
    this.observationOrder = ordenDetails.observations;
    this.ordenDetails = ordenDetails
    this.showObservation = true
  }

}
