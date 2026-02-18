import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { SysUser } from '@auth/models/sys-user';
import { ResponseOrders, ResponseOrdersToDownload } from 'src/app/medical-order-management/models/response-orders';
import { DictionaryStatusOrderPipe } from 'src/app/medical-order-management/pipes/dictionary-status-order.pipe';
import { MedicalRequestManagementService } from '@medication-request/services/medical-request-management.service';
import { Paginator } from '@shared/models/paginator';
import { FilterMedicalRequestComponent } from '@medication-request/components/filter-medical-request/filter-medical-request.component';
import { FilterMedicalRequestManagement } from '@medication-request/models/filter-medical-request-management.';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { DetailMedicalRequestComponent } from '@medication-request/components/detail-medical-request/detail-medical-request.component';
import { DataSetUpManagement, StatusChangeRequestData } from '@medication-request/models/data-set-up-management';
import { PaginatorModule } from 'primeng/paginator';
import { MovementsMedicalRequest } from '@medication-request/models/movements-medical-request';
import { GetStylesStatusOrderPipe, OrderStatusColorPipe } from '@shared/pipes/get-styles-status-order.pipe';
import { FileManagementService } from '@medication-request/services/file-management.service';
import { DataFileResource } from '@shared/models/data-file-resource';
import { StatusOrder } from '@shared/models/status-order';
import { DictionaryTypeClientPipe } from '@medication-request/pipes/dictionary-type-client.pipe';
import { DownloadExcelService } from '@medication-request/services/download-excel.service';

@Component({
  selector: 'app-medication-request-management',
  templateUrl: './medication-request-management.component.html',
  styleUrls: ['./medication-request-management.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    TableBaseStylesComponent,
    TableModule,
    DictionaryStatusOrderPipe,
    FilterMedicalRequestComponent,
    ButtonModule,
    DialogModule,
    DetailMedicalRequestComponent,
    PaginatorModule,
    GetStylesStatusOrderPipe,
  ],
  providers:[
    OrderStatusColorPipe,
    DictionaryTypeClientPipe,
    DictionaryStatusOrderPipe,
    DownloadExcelService,
    DictionaryTypeClientPipe
  ]
})
export class MedicationRequestManagementComponent implements OnInit {
  public dataUserInSession: SysUser;
  public reponseOrderToManagement: ResponseOrders[] = []
  public reponseOrderToDownload: ResponseOrdersToDownload[] = []
  public paginator: Paginator = new Paginator();
  public loadingSearch: boolean = false;
  public loadingSetUpManagement: boolean = false;
  public loadingManagementOrder: boolean = false;
  public showDialog: boolean = false;
  public medicalRequestDetail: ResponseOrders;
  public showDialogSetUpManagement: boolean = false;
  public filtersMedicalRequest: FilterMedicalRequestManagement
  public movementsMedicalRequest: MovementsMedicalRequest[] = [];
  public loadingDownload : boolean = false;
  constructor(
    private notificationService: NotificationService,
    private medicalRequestService: MedicalRequestManagementService,
    private sessionService: SessionService,
    private fileManagementService: FileManagementService,
    private downloadExcelService : DownloadExcelService
    
  ) { }

  ngOnInit(): void {
    this.dataUserInSession = this.sessionService.userData
    this.adapterGetMedicalOrder(null, new Paginator().page, new Paginator().row)
  }


  getMedicalOrder(filters: FilterMedicalRequestManagement, page: number, size: number) {
    this.loadingSearch = true;
    this.medicalRequestService.getMedicalOrderToManagement(filters, page, size).subscribe({
      next: (response) => {
        this.loadingSearch = false;
        if (response.success) {
          this.reponseOrderToManagement = response.data
        }
        this.paginator = {
          page: response.number,
          row: size,
          totalPages: response.totalPages,
          totalRecords: response.totalElements
        }
      },
      error: (error) => {
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
        this.loadingSearch = false;
      },
    })
  }


  getMedicalRequestById(order: ResponseOrders) {
    if (!order) return;
    this.loadingManagementOrder = true
    this.medicalRequestService.getMedicalRequestById(order.id).subscribe({
      next: (response) => {
        this.loadingManagementOrder = false;
        if (response.success) {
          this.medicalRequestDetail = response.data
          this.showDialog = true;
          this.getMovementsMedicalRequest(this.medicalRequestDetail)
        }
      },
      error: (error) => {
        this.loadingManagementOrder = false;
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
      },
    })
  }

  searchResults(filters: FilterMedicalRequestManagement) {
    this.adapterGetMedicalOrder(filters, this.paginator.page, this.paginator.row)
  }

  adapterGetMedicalOrder(filter: FilterMedicalRequestManagement, page: number, row: number) {
    this.getMedicalOrder(filter, page, row)
  }

  cleanFilter() {
    this.reponseOrderToManagement = [];
  }


  showDialogManagement() {
    this.showDialogSetUpManagement = true;
  }

  closet() {
    this.showDialog = false;
    this.showDialogSetUpManagement = false;
    this.adapterGetMedicalOrder(this.filtersMedicalRequest, this.paginator.page, this.paginator.row)
  }

  setUpManagement(dataSetUpManagement: DataSetUpManagement) {
    this.setNewStatusMedicalRequest(dataSetUpManagement)
  }


  setNewStatusMedicalRequest(dataSetUpManagement: DataSetUpManagement) {
    this.loadingSetUpManagement = true;
    const idEmployee = this.dataUserInSession?.userId
    const nameEmployee = this.dataUserInSession?.name + this.dataUserInSession?.lastName
    const statusChangeRequestData: StatusChangeRequestData = {
      comments: dataSetUpManagement.descriptionByAdmin,
      statusOrder: dataSetUpManagement.stateOrderByAdmin,
      idEmployee: idEmployee,
      nameEmployee: nameEmployee,
      // VARIABLES NUEVAS DE PENDIENTE
      partial: dataSetUpManagement.partial,  
      totalPending: dataSetUpManagement.totalPending,
      detailsPending: dataSetUpManagement.detailsPending,
    }
    this.medicalRequestService.updateStateMedicalRequest(this.medicalRequestDetail.id, statusChangeRequestData).subscribe({
      next: (response) => {
        this.loadingSetUpManagement = false;
        this.showDialogSetUpManagement = false;
        if (response.success) {
          this.closet()
          this.notificationService.modalBasic(
            'Realizado con éxito',
            'La solicitud médica fue actualizada',
            'success',
          )
        }
      },
      error: (error) => {
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
        this.loadingSetUpManagement = false;
      },
    })
  }

  cancelManagement() {
    this.showDialogSetUpManagement = false;
  }


  onPageChange1(paginator) {
    this.adapterGetMedicalOrder(this.filtersMedicalRequest, paginator.page + 1, this.paginator.row)
  }


  getMovementsMedicalRequest(order: ResponseOrders) {
    if (!order) return;
    this.medicalRequestService.getMovementsMedicalRequest(order.id).subscribe({
      next: (response) => {
        if (response.success) {
          this.movementsMedicalRequest = response.data
        }
      },
      error: (error) => {
        console.log('🚀 ~ error:', error)
        this.movementsMedicalRequest = []
        this.notificationService.toasErrorI18n(error)
      },
    })
  }


  getResource(dataFileResource: DataFileResource) {
    this.fileManagementService.getFileResource(dataFileResource.fileName, dataFileResource.path)
  }

  exportData() {
    this.getMedicalOrdersToDownload(this.filtersMedicalRequest);
  }

  getMedicalOrdersToDownload(filters: FilterMedicalRequestManagement) {
    this.loadingDownload = true;
    this.medicalRequestService.getMedicalOrderToDownload(filters).subscribe({
      next: (response) => {
        this.loadingDownload = false;
        if (response.success) {
          this.reponseOrderToDownload = response.data
          this.downloadExcelService.downloadData(this.reponseOrderToDownload);
        }
      },
      error: (error) => {
        this.loadingDownload = false;
        console.log('🚀 ~ error:', error)
        this.notificationService.toasErrorI18n(error)
      },
    })
  }

  get statusOrder() {
    return StatusOrder
  }

}
