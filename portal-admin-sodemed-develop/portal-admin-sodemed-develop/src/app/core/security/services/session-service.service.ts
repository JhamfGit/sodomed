import { Injectable } from '@angular/core';
import { SysUser } from 'src/app/auth/models/sys-user';
import { RAW_TOKEN } from '../models/keys-statics';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private router: Router) { }


  setDataLocalStorage(sysUser: SysUser): boolean {
    let validSetData: boolean = false;
    if (!sysUser) {
      throw new Error("Sys User not valid ");
    }
    if (sysUser) {
      localStorage.removeItem(RAW_TOKEN);
      localStorage.setItem(RAW_TOKEN, JSON.stringify(sysUser));
      validSetData = true;
    }
    return validSetData
  }


  /**
  * redirige segun la configuracion al proceso de salida del sistema
  */
  logout() {
    this.router.navigate(['/auth/login']);
  }

  /**
  * desacoplamiento total del dispositivo al usuario
  */
  disengage(): Promise<void> {
    return Promise.all([
      this.clearSession()
    ]).then(data => { });
  }

  /**
  * elimina datos de la session actual
  */
  clearSession() {
    return localStorage.removeItem(RAW_TOKEN);
  }

  exitSession() {
    this.clearSession()
    this.logout()
  }

  /**
  * determina si el usuario actual se encuentra logeado 
  * en el sistema
  */
  get isLoggedIn(): boolean {
    const sysUser = this.read<SysUser>(RAW_TOKEN)
    return sysUser && sysUser.accesToken && true;
  }

  /**
 * determina el token de la session del usuario
 */
  get sessionToken(): string {
    const user = this.read<SysUser>(RAW_TOKEN);
    return user ? user.accesToken : null;
  }


  /**
 * informacion del usuario de la session actual siempre y cuando se encuentre logueado 
 * de lo contrario retornara null
 */
  get userData(): SysUser {
    return this.read<SysUser>(RAW_TOKEN) || <any>{};
  }

  private write<T>(key: string, value: T): T {
    let str_value = null
    if (value) {
      str_value = JSON.stringify(value);
    }
    localStorage.setItem(key, str_value);
    return value;
  }

  private read<T>(key: string): T {
    let value: any = localStorage.getItem(key);
    if (value && value !== 'undefined' && value !== 'null') {
      value = <T>JSON.parse(value);
    } else {
      value = null;
    }
    return value;
  }

  updateDataSysUser(target: SysUser, source: Partial<SysUser>) {
    Object.keys(source).forEach(key => {
      const typedKey = key as keyof SysUser;
      const value = source[typedKey];
      if (value !== null && value !== undefined) {
        target[typedKey as any] = value as SysUser[keyof SysUser];
      }
    });
    localStorage.setItem(RAW_TOKEN, JSON.stringify(target));
  }


  /**
 * determina si el usuario tiene todos los permisos pasados
 * @param getpermissions permisos a evaluar
 */
  hasPermission(getpermissions: string[]): { user: SysUser, have: boolean } {
    const user = this.userData;
    return {
      user: user,
      have: user && user.permissions && user.permissions.filter(permission => getpermissions.indexOf(permission) > -1).length == getpermissions.length
    };
  }

  /**
   * determina si el usuario tiene alguno de los permisos pasados 
   * @param getpermissions permisos a evaluar
   */
  hasAnyPermission(getPermissions: string[]): { user: SysUser, have: boolean } {
    const user = this.userData;

    // Validar si el usuario tiene alguno de los permisos requeridos
    const havePermission = getPermissions.some(permission =>
      user?.permissions?.includes(permission)
    );

    return {
      user: user,
      have: havePermission
    };
  }

}
