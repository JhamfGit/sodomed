import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponseData } from '@core/response/response-data';
import { catchError, Observable, throwError } from 'rxjs';
import { MedicalRequestData } from '../models/medical-request-data';
import { ResponseDataList } from '@core/response/response-data-list';
import { StatusOrderType } from '@shared/models/status-order';
import { ResponseOrders } from '@medication-request/models/response-orders';
import { HttpBaseService } from '@core/services/http-base.service';

@Injectable({
  providedIn: 'root'
})
export class MedicalRequestService extends HttpBaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  sendMedicalRequest(fileIdentification, fileMedicationOrder, fileMedicalHistory, medicationOrder: any): Observable<ResponseData<any>> {
    let formData = new FormData();
    formData.append("fileIdentification", fileIdentification);
    formData.append("fileMedicationOrder", fileMedicationOrder);
    formData.append("fileMedicalHistory", fileMedicalHistory);
    formData.append("medicationOrder", new Blob([JSON.stringify(medicationOrder)], { type: 'application/json' }));
    return this.httpClient.post<ResponseData<any>>(this.getUrlFor("/"), formData)
      .pipe(
        catchError(this.handleError)
      );
  }


  getOrdesByIdUserCreateAndStatusOrdes(idUserCreate: number | string, statusOrderType: StatusOrderType): Observable<ResponseDataList<ResponseOrders>> {
    return this.httpClient.get<ResponseDataList<ResponseOrders>>(this.getUrlFor(`/${idUserCreate}/pending`), { params: { statusOrder: statusOrderType } })
      .pipe(
        catchError(this.handleError)
      );
  }

  getOrdesHistoryByIdUser(idUserCreate: number | string): Observable<ResponseDataList<ResponseOrders>> {
    return this.httpClient.get<ResponseDataList<ResponseOrders>>(this.getUrlFor(`/${idUserCreate}/history`))
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteMedicalRequest(idMedicalRequest: number): Observable<ResponseData<ResponseOrders>> {
    return this.httpClient.delete<ResponseData<ResponseOrders>>(this.getUrlFor(`/${idMedicalRequest}`))
      .pipe(
        catchError(this.handleError)
      );
  }

  get serviceName() {
    return 'medication'
  }

}
