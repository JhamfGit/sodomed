import { Pipe, PipeTransform } from '@angular/core';
import { EnumStatusUser } from '../models/status-user';

@Pipe({
  name: 'translateStatusUser',
  standalone : true,
})
export class TranslateStatusUserPipe implements PipeTransform {

  private translations: { [key: string]: string } = {
    [EnumStatusUser.Active]: 'Activo',
    [EnumStatusUser.Inactive]: 'Inactivo'
  };

  transform(value: EnumStatusUser): string {
    return this.translations[value] || 'Desconocido';
  }
}