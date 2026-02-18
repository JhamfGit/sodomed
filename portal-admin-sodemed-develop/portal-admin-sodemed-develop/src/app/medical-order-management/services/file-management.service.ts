import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponseData } from '@core/response/response-data';
import { HttpBaseService } from '@core/services/http-base.service';

@Injectable({
  providedIn: 'root'
})
export class FileManagementService extends HttpBaseService {
  private httpClient: HttpClient;


  constructor(private httpBackend: HttpBackend) {
    super();
    this.httpClient = new HttpClient(this.httpBackend);
  }


  getFileResource(filename: string , path: string){
     const urlResourceFile =`${this.getUrlFor(`/resource/${filename}`)}?mediaLocation=${path}`;
     window.open(urlResourceFile,'_blank')
  }
  
  
  get serviceName(): string {
    return 'medication'
  }
}
