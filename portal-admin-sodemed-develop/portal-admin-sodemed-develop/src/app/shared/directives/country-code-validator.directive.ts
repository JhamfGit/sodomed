import { Directive, HostListener, ElementRef, Renderer2, OnInit } from '@angular/core';

@Directive({
  selector: '[appCountryCodeNumber]',
  standalone: true
})
export class CountryCodeNumberDirective implements OnInit {

  private el: HTMLInputElement;

  constructor(private elementRef: ElementRef, private renderer: Renderer2) {
    this.el = this.elementRef.nativeElement;
  }

  ngOnInit(): void {
    this.ensurePlusSign();
  }

  // Asegurar que el "+" esté siempre presente al inicio
  private ensurePlusSign(): void {
    const value = this.el.value;
    if (!value.startsWith('+')) {
      this.renderer.setProperty(this.el, 'value', `+${value}`);
    }
  }

  @HostListener('input', ['$event'])
  onInput(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    let value = inputElement.value;

    // Asegurarse de que el valor siempre empiece con '+'
    if (!value.startsWith('+')) {
      value = `+${value}`;
    }

    // Remover todos los caracteres que no sean numéricos después del '+'
    value = `+${value.substring(1).replace(/\D/g, '')}`;
    this.renderer.setProperty(this.el, 'value', value);
  }

  @HostListener('keypress', ['$event'])
  onKeyPress(event: KeyboardEvent): void {
    const inputElement = event.target as HTMLInputElement;
    const value = inputElement.value;

    // Permitir solo el "+" al inicio, y números después
    if (!/[0-9]/.test(event.key) && !(value.length === 0 && event.key === '+')) {
      event.preventDefault(); // Bloquear cualquier tecla que no sea número o el "+"
    }
  }

  @HostListener('blur')
  onBlur(): void {
    this.ensurePlusSign();
  }

  @HostListener('focus')
  onFocus(): void {
    this.ensurePlusSign();
  }
}
