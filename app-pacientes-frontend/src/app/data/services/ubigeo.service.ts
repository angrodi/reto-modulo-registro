import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UbigeoService {
  private apiUrl: string = environment.apiUrl;

  constructor(
    private httpClient: HttpClient
  ) { }

  public getDepartamentos(): Observable<any> {
    return this.httpClient.get(this.apiUrl + '/ubigeo/departamentos');
  }

  public getProvincias(codDepartamento: string): Observable<any> {
    const params = new HttpParams()
      .set('codDepartamento', codDepartamento)

    return this.httpClient.get(this.apiUrl + '/ubigeo/provincias', {params});
  }

  public getDistritos(codDepartamento: string, codProvincia: string): Observable<any> {
    const params = new HttpParams()
      .set('codDepartamento', codDepartamento)
      .set('codProvincia', codProvincia)

    return this.httpClient.get(this.apiUrl + '/ubigeo/distritos', {params});
  }
}
