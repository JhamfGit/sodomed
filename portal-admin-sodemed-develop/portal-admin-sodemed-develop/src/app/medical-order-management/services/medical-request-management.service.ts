import { HttpClient } from '@angular/common/http';
import { Injectable, PipeTransform } from '@angular/core';
import { ResponseData } from '@core/response/response-data';
import { catchError, delay, Observable, throwError } from 'rxjs';
import { MedicalRequestData } from '../models/medical-request-data';
import { ResponseDataList } from '@core/response/response-data-list';
import { StatusOrderType } from '@shared/models/status-order';
import { ResponseOrders, ResponseOrdersToDownload } from 'src/app/medical-order-management/models/response-orders';
import { HttpBaseService } from '@core/services/http-base.service';
import { ResponsePaginable } from '@core/response/response-paginable';
import { FilterMedicalRequestManagement } from '@medication-request/models/filter-medical-request-management.';
import { DatePipe } from '@angular/common';
import { SharedConstants } from '@shared/models/shared-constants';
import { BuildQueryParams } from '@shared/models/build-params';
import { DataSetUpManagement, StatusChangeRequestData } from '@medication-request/models/data-set-up-management';
import { MovementsMedicalRequest } from '@medication-request/models/movements-medical-request';

@Injectable({
  providedIn: 'root',

})
export class MedicalRequestManagementService extends HttpBaseService {

  constructor(
    private httpClient: HttpClient,
    private datePipe: DatePipe
  ) {
    super();
  }
  getMedicalOrderToManagement(filters: FilterMedicalRequestManagement, page: number, size: number): Observable<ResponsePaginable<ResponseOrders>> {
    const paramsReuquest = {
      start_date: filters?.startDate ? this.datePipe.transform(filters.startDate, SharedConstants.FORMAT_DATE) : null,
      end_date: filters?.endDate ? this.datePipe.transform(filters.endDate, SharedConstants.FORMAT_DATE) : null,
      statusOrder: filters?.statusOrder ?? null,
      userIdentification: filters?.identification ?? null,
      page: page,
      size: size
    }
    const newPrams = BuildQueryParams.buildQueryObject(paramsReuquest)

    return this.httpClient.get<ResponsePaginable<ResponseOrders>>(this.getUrlFor(`/fetch`), { params: newPrams })
      .pipe(
        catchError(this.handleError)
      );
  }

  takeMedicalREquestByAdmin(idMedicalRequest: number, idEmployee: number, nameEmployee: string): Observable<ResponseData<ResponseOrders>> {
    return this.httpClient.put<ResponseData<ResponseOrders>>(this.getUrlFor(`/take/${idMedicalRequest}`), null, { params: { idEmployee, nameEmployee } })
      .pipe(
        catchError(this.handleError)
      );
  }


  getMedicalRequestById(idMedicalRequest: number): Observable<ResponseData<ResponseOrders>> {
    return this.httpClient.get<ResponseData<ResponseOrders>>(this.getUrlFor(`/${idMedicalRequest}`))
      .pipe(
        catchError(this.handleError)
      );
  }

  updateStateMedicalRequest(idMedicalRequest: number, dataSetUpManagement: StatusChangeRequestData): Observable<ResponseData<ResponseOrders>> {
    return this.httpClient.put<ResponseData<ResponseOrders>>(this.getUrlFor(`/partial/update/${idMedicalRequest}`), dataSetUpManagement)
      .pipe(
        catchError(this.handleError)
      );
  }


  getMovementsMedicalRequest(idMedicalRequest: number): Observable<ResponseDataList<MovementsMedicalRequest>> {
    return this.httpClient.get<ResponseDataList<MovementsMedicalRequest>>(this.getUrlFor(`/movements/${idMedicalRequest}`))
      .pipe(
        catchError(this.handleError)
      );
  }

  getMedicalOrderToDownload(filters: FilterMedicalRequestManagement): Observable<ResponsePaginable<ResponseOrdersToDownload>> {
    const paramsReuquest = {
      start_date: filters?.startDate ? this.datePipe.transform(filters.startDate, SharedConstants.FORMAT_DATE) : null,
      end_date: filters?.endDate ? this.datePipe.transform(filters.endDate, SharedConstants.FORMAT_DATE) : null,
      statusOrder: filters?.statusOrder ?? null,
      userIdentification: filters?.identification ?? null,
    }
    const newPrams = BuildQueryParams.buildQueryObject(paramsReuquest)

    return this.httpClient.get<ResponsePaginable<ResponseOrders>>(this.getUrlFor(`/download-data`), { params: newPrams })
      .pipe(
        catchError(this.handleError)
      );
  }

  get serviceName() {
    return 'medication'
  }

}
