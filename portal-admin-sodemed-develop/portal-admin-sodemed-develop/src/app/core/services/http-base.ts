import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ErrorResponse } from '@core/response/error-response';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
/**
 * estructura base del enviroment para la obtencion de los datos
 * de conexion con los servicios
 */
export interface EnvironmentBase {
  production: boolean;
  services: {
    endpoint: string,
    [key: string]: any
  },
  [key: string]: any
}

export class HttpBase {
  protected root = '';

  constructor() {
    this.root = this.environment.services.endpoint;
  }

  /**
   * retorna la configuracion base de conexion del enviroment
   */
  get environment(): EnvironmentBase {
    return environment;
  }

  /**
   * retorna la url base para acceder a las consultas http
   */
  protected getRootUrl(): string {
    return this.root ? this.root : 'ERROR_URL_ENDPOINT_ROOT_NOT_FOUND';
  }

  /**
  * concatena la url base con la pasada
  * @param _for url a concatenar con la base
  */
  protected getUrlFor(_for: string = ''): string {
    return _for ? this.getRootUrl() + _for : this.getRootUrl();
  }

  /**
   * Manejo de errores globales en los servicios
   */
  protected get handleError(): (error: any) => Observable<any> {
    return (error: any) => {
      let response = new ErrorResponse();
      if (error instanceof HttpErrorResponse) {
        const errorResponse = error?.error ?? null;
        const errorNotify = errorResponse?.error as ErrorResponse ?? null;
        if (errorNotify) {
          return throwError(() => errorNotify);
        } else {
          switch (error.status) {
            case 500: {
              response = { title: 'errors.net.500.title', code: 'INTERNAL_ERROR', message: 'errors.net.500.description' };// TODO cambiar nombre al error 
              return throwError(() => response); // Añadir return aquí para evitar fallthrough
            }
            case 0: {
              response = { title: 'Sucedió un error inesperado', code: 'UNKNOW', message: 'Por favor revise la conexión a internet o intente más tarde' };
              return throwError(() => response); // Añadir return aquí para evitar fallthrough
            }
            default:
              return throwError(() => errorNotify);
          }
        }
      }
      // Manejo de errores para casos que no son HttpErrorResponse
      response = { title: 'Sucedió un error inesperado', code: 'UNKNOW', message: 'Por favor revise la conexión a internet o intente más tarde' };
      return throwError(() => error);
    }
  }
}
