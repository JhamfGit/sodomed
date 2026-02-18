import { Injectable } from '@angular/core';
import { HttpBase } from './http-base';

@Injectable({
    providedIn: 'root'
})
export abstract class HttpBaseService extends HttpBase {

    constructor() {
        super();
    }
    /**
   * determina la url para el servicio segun el nombre pasado
   */
    protected override getRootUrl(): string {
        if (this.serviceName != null) {
            let config = this.environment.services[this.serviceName];
            if (config && config.endpoint) {
                return config.endpoint.replace('$root', this.root);
            }
        }
        return `${super.getRootUrl()}/ERROR_NAME_SERVICE_NOT_FOUND`;
    }

    /**
     * retorna la url pasada contextualizada
     * a la base url configurada para el servicio pasado
     * @param service nombre del servicio a acceder
     * @param _for url a concatenar
     * @returns url concatenada a la base del servicio enviado
     */
    protected getUrlForService(service: string, _for: string = ''): string {
        if (service) {
            let config = this.environment.services[service];
            if (config && config.endpoint) {
                let base = config.endpoint.replace('$root', this.root);
                return `${base}${_for ? _for : ''}`
            }
        }
        return null;
    }

    /**
     * determina el nombre del servicio
     * este se usara para localizar configuraciones personalizadas en  
     * el enviroment
     */
    abstract get serviceName(): string;

}
