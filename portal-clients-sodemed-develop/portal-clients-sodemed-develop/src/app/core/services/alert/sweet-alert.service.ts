import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { ErrorResponse } from '../../response/error-response';

export type SweetAlertIcon = 'success' | 'error' | 'warning' | 'info' | 'question'
@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  constructor() { }

  /**
   * Sweet simple, confirmacion de ok y resive un callback opcional
   * @param title
   * @param text
   */
  modalBasic(title: string, text: string, icon: SweetAlertIcon = 'info', callback?: () => void) {
    Swal.fire(
      title,
      text,
      icon
    ).then((result) => {
      if (callback) callback()
    });
  }

  /**
   *  Toast sweet para mensaje de success en modo toast
   * @param title
   * @param text
   */
  toasAlertSuccess(title: string, text: string, timer: number = 2000) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: timer,
      timerProgressBar: true,
    });

    Toast.fire({
      icon: 'success',
      title: title,
      text: text,
    });
  }

  /**
   * Toast error con traductor
   * @param title
   * @param text
   */
  toasErrorI18n(
    error: ErrorResponse,
  ) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      showCloseButton: true,
    });
    Toast.fire({
      icon: 'error',
      customClass: {
        container: 'zIndexError',

      },
      title: error.title,
      text: error.message,
    });
  }

  /**
   * Toast sweet modo toast generico
   * @param title
   * @param text
   * @param icon
   */
  toasGeneric(title: string, text: string, icon: SweetAlertIcon, timer = null) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: timer,
      timerProgressBar: true,
    });
    Toast.fire({
      customClass: {
        container: 'zIndexError',

      },
      icon: icon,
      title: title,
      text: text,
    });
  }

  /**
   * Toast success default
   * @param text
   */
  toastSuccessDefault(text?: string, timer: number = 2000) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: timer,
      timerProgressBar: true,
    });
    Toast.fire({
      icon: 'success',
      // title: this.i18nService.t('Realizado con éxito'),
      title: 'Realizado con éxito',
      text: text,
      customClass: {
        container: 'zIndexError',

      },
    });
  }
  /**
  * Toast Error default
  * @param text
  */
  toastErrorDefault(text?: string, _icon: 'error' | 'info' = 'error') {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timerProgressBar: true,
    });
    Toast.fire({
      icon: _icon,
      title: 'error',
      text: text ?? 'error',
      customClass: {
        container: 'zIndexError',

      },
    });
  }

  modalConfirmAction({
    title,
    text,
    icon,
    confirmButtonText = 'Aceptar',
    cancelButtonText = 'Cancelar',
    onConfirm,
    onCancel = null,
    showCancelButton = true,
    allowOutsideClick = true,
    allowEscapeKey = true
  }: {
    title: string,
    text: string,
    icon: SweetAlertIcon,
    onConfirm?: () => void,
    onCancel?: () => void
    confirmButtonText?: string,
    cancelButtonText?: string,
    showCancelButton?: boolean
    allowOutsideClick?: boolean,  // Desactiva el cierre al hacer clic fuera
    allowEscapeKey?: boolean,
  }) {
    Swal.fire({
      title,
      html: text,
      icon,
      showCancelButton: showCancelButton,
      confirmButtonText,
      cancelButtonText,
      reverseButtons: true,
      allowOutsideClick: allowOutsideClick,  // Desactiva el cierre al hacer clic fuera
      allowEscapeKey: allowEscapeKey,
      customClass: {
        container: 'zIndexError',

      },

    }).then(result => {
      if (result.isConfirmed) {
        onConfirm?.();
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        onCancel?.();
      }
    });
  }
}
