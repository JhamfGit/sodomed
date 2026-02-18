import { Pipe, type PipeTransform } from '@angular/core';
import { StatusOrder, StatusOrderType } from '@shared/models/status-order';
const translationsColor: { [key in StatusOrder]: string } = {
  [StatusOrder.Pending]: '#2c2a29',
  [StatusOrder.Processing]: '#f39c12',
  [StatusOrder.Accept]: '#27ae60',
  [StatusOrder.Send]: '#2980b9',
  [StatusOrder.Reject]: '#e74c3c',
  [StatusOrder.Delivered]: '#8e44ad'
};

@Pipe({
  name: 'getStylesStatusOrder',
  standalone: true,
})
export class GetStylesStatusOrderPipe implements PipeTransform {

  private translations: { [key in StatusOrder]: {
    "background-color": string;
  } } = {
      [StatusOrder.Pending]: { 'background-color': translationsColor[StatusOrder.Pending] },
      [StatusOrder.Processing]: { 'background-color': translationsColor[StatusOrder.Processing] },
      [StatusOrder.Accept]: { 'background-color': translationsColor[StatusOrder.Accept] },
      [StatusOrder.Send]: { 'background-color': translationsColor[StatusOrder.Send] },
      [StatusOrder.Reject]: { 'background-color': translationsColor[StatusOrder.Reject] },
      [StatusOrder.Delivered]: { 'background-color': translationsColor[StatusOrder.Delivered] }
    };

  transform(value: StatusOrderType) {
    return this.translations[value] ?? '';
  }

}


@Pipe({
  name: 'orderStatusColor',
  standalone: true,
})
export class OrderStatusColorPipe implements PipeTransform {
  transform(value: StatusOrder): string {
    return translationsColor[value] ?? '';
  }
}