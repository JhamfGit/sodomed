import { DatePipe } from '@angular/common';
import { Injectable } from '@angular/core';
import { ResponseOrders, ResponseOrdersToDownload } from '@medication-request/models/response-orders';
import { DictionaryStatusOrderPipe } from '@medication-request/pipes/dictionary-status-order.pipe';
import { DictionaryTypeClientPipe } from '@medication-request/pipes/dictionary-type-client.pipe';
import { SharedConstants } from '@shared/models/shared-constants';
import { StatusOrder } from '@shared/models/status-order';
import { exportExcel as EXPORT_EXCEL } from 'src/app/utils/excel';

@Injectable({
  providedIn: 'root'
})
export class DownloadExcelService {

  constructor(
    private datePipe: DatePipe,
    private dictionaryStatusOrderPipe: DictionaryStatusOrderPipe,
    private dictionaryTypeClientPipe: DictionaryTypeClientPipe
  ) { }

  downloadData(downloadData: ResponseOrdersToDownload[] = []) {
    return new Promise(resolve => {
      let newArray: any[] = [];
      let EXCEL_EXTENSION = '.xlsx';
      let name = `Reporte_solicitudes${new Date().toLocaleDateString()}_${new Date().getTime()}_${EXCEL_EXTENSION}`;
      downloadData.map((data: ResponseOrdersToDownload) => {
        newArray.push(this.mapDataForDownload(data));
      })
      EXPORT_EXCEL(newArray, name);
      resolve(null);
    })
  }

  mapDataForDownload(data: ResponseOrdersToDownload): any {
    const mappedData = {
      "activo": data?.active ? "Activada" : "Inactiva",
      "fecha de creación": this.datePipe.transform(data?.creationDate, SharedConstants.FORMAT_DATE_W_HOURS),
      "estado de la orden": this.dictionaryStatusOrderPipe.transform(data?.statusOrder) ?? '',
      "tipo de envio": this.returnTypeSent(data?.statusOrder, data?.partial ?? false),
      "detalles pendientes": data?.detailsPending ?? '',
      "detalle de G. solicitud": data?.observations ?? '',
      "total pendiente": data?.totalPending ?? '',
      "tipo de cliente": this.dictionaryTypeClientPipe.transform(data?.typeClient ?? ('' as any)),
      "identificación del usuario que crea la orden": data?.userOrderIdentification ?? '',
      "nombre del usuario que crea la orden": data?.userOrderName ?? '',
      "email del usuario que crea la orden": data?.userCreateEmail ?? '',
      "telefono del usuario que crea la orden": data?.userCreatePhone ?? '',
      "código de país del usuario que crea la orden": data?.userCreateCountryDialCode ?? '',
      "nombre del empleado": data?.nameEmployee ?? '',
      "id del empleado": data?.idEmployee ?? '',
      "dirección domiciliaria": data?.homeAddress ?? '',
      "ciudad domiciliaria": data?.homeCity ?? '',
      "barrio": data?.district ?? '',
      "indicaciones adicionales del usuario que crea la orden": data?.furtherDirectionIndication ?? '',
      "observaciones del usuario que crea la orden": data?.comments,
      "última fecha de modificación": this.datePipe.transform(data?.lastModificationDate, SharedConstants.FORMAT_DATE_W_HOURS),
      "URL de archivos": data?.urlFiles ?? '',
      "URL de archivo de identificación": data?.urlIdentificationFile ?? '',
      "URL de historial médico": data?.urlMedicalHistoryFile ?? '',
      "URL de orden médica": data?.urlMedicationOrderFile ?? '',
    };
    return mappedData;
  }


  hasBeenSent(statusOrder: StatusOrder): boolean {
    return statusOrder === StatusOrder.Send
  }


  returnTypeSent(statusOrder: StatusOrder, partial: boolean): string {
    const hasBeenSent = this.hasBeenSent(statusOrder);
    if (!hasBeenSent) {
      return 'sin enviar'
    }
    return partial ? 'parcial' : 'completa'
  }
}
