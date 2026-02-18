import { Pipe, type PipeTransform } from '@angular/core';
import { StatusOrder } from '@shared/models/status-order';

@Pipe({
  name: 'dictionaryStatusOrder',
  standalone: true,
})
export class DictionaryStatusOrderPipe implements PipeTransform {

  private translations: { [key in StatusOrder]: string } = {
    [StatusOrder.Pending]: 'Pendiente',
    [StatusOrder.Processing]: 'En proceso',
    [StatusOrder.Accept]: 'Aceptado',
    [StatusOrder.Send]: 'Enviado',
    [StatusOrder.Reject]: 'Rechazado',
    [StatusOrder.Delivered]: 'Entregado'
  };

  transform(value: StatusOrder): string {
    return this.translations[value] ?? value;
  }

}
