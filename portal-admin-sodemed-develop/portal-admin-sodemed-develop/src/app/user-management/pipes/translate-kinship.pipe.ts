import { Pipe, PipeTransform } from '@angular/core';
import { EnumKinship } from '../models/kinship';

@Pipe({
  name: 'translateKinship',
  standalone: true,
})
export class TranslateKinshipPipe implements PipeTransform {

  private translations: { [key: string]: string } = {
    [EnumKinship.Son]: 'Hijo/a',
    [EnumKinship.Partner]: 'Pareja',
    [EnumKinship.Father]: 'Padre',
    [EnumKinship.Mother]: 'Madre',
  };

  transform(value: EnumKinship): string {
    return this.translations[value] ?? value;
  }
}
