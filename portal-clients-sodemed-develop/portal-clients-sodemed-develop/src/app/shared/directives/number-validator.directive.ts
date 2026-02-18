import { Directive, ElementRef, HostListener, Input, Renderer2, OnInit } from '@angular/core';

@Directive({
  selector: '[appNumberValidator]',
  standalone: true
})
export class NumberValidatorDirective implements OnInit {


  @Input() minLength: number = 0;  // Mínimo número de caracteres permitidos
  @Input() maxLength: number = Infinity;  // Máximo número de caracteres permitidos
  @Input() allowNegative: boolean = false;  // Permitir números negativos
  @Input() minValue: number = -Infinity;  // Valor mínimo permitido
  @Input() maxValue: number = Infinity;  // Valor máximo permitido

  private inputElement: HTMLInputElement;

  constructor(private el: ElementRef, private renderer: Renderer2) {
    this.inputElement = this.el.nativeElement;
  }

  ngOnInit(): void {
    this.renderer.setAttribute(this.inputElement, 'maxlength', this.maxLength.toString());  // Limitar caracteres en el input
  }

  @HostListener('input', ['$event'])
  onInputChange(event: Event): void {
    let value = this.inputElement.value;

    // Si no se permiten números negativos, eliminar el signo negativo
    if (!this.allowNegative) {
      value = value.replace(/[^0-9]/g, '');
    } else {
      value = value.replace(/[^0-9-]/g, '');
    }

    // Limitar el valor según los caracteres permitidos
    if (value.length > this.maxLength) {
      value = value.slice(0, this.maxLength);
    }

    // Convertir el valor a número y validar si está dentro del rango permitido
    const numericValue = Number(value);
    if (numericValue < this.minValue) {
      value = this.minValue.toString();
    } else if (numericValue > this.maxValue) {
      value = this.maxValue.toString();
    }

    this.inputElement.value = value;
  }

  @HostListener('keypress', ['$event'])
  onKeyPress(event: KeyboardEvent): void {
    const key = event.key;

    // Permitir números, backspace y guión para números negativos
    if (!/[0-9-]/.test(key) || (key === '-' && !this.allowNegative)) {
      event.preventDefault();
    }

    // Evitar que se exceda el número máximo de caracteres
    if (this.inputElement.value.length >= this.maxLength) {
      event.preventDefault();
    }
  }

  

}
