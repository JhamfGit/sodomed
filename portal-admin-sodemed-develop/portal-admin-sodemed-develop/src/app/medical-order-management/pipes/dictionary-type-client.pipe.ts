import { Pipe, type PipeTransform } from '@angular/core';
import { EnumTypeClient, TypeClient } from '@core/models/type-client';

@Pipe({
  name: 'dictionaryTypeClient',
  standalone: true,
})
export class DictionaryTypeClientPipe implements PipeTransform {

  private translations: { [key in EnumTypeClient]: string } = {
    [EnumTypeClient.Beneficiary]: 'Beneficiario',
    [EnumTypeClient.Contizing]: 'Cotizante',

  };

  transform(value: TypeClient): string {
    return this.translations[value] ?? value;
  }

}
