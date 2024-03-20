import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Paciente } from '../types/Paciente';
import { PacienteAcompanante } from '../types/PacienteAcompanante';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {
  private apiUrl: string = environment.apiUrl;

  constructor(
    private httpClient: HttpClient
  ) { }

  public createPaciente(paciente: Paciente, acompanante: PacienteAcompanante | null): Observable<any> {
    const payload = { paciente, acompanante };
    return this.httpClient.post(this.apiUrl + '/paciente', payload);
  }

  public getPacientes(): Observable<any> {
    return this.httpClient.get(this.apiUrl + '/paciente');
  }

  public getPacienteByDocIdentidad(tipoDocIdentidad: number, docIdentidad: string): Observable<any> {
    const params = new HttpParams()
      .set('tipoDocIdentidad', tipoDocIdentidad)
      .set('docIdentidad', docIdentidad)

    return this.httpClient.get(this.apiUrl + '/paciente/docidentidad', {params});
  }

  public getPacienteByNombresAndApellidos(nombres: string, apellidos: string): Observable<any> {
    const params = new HttpParams()
      .set('nombres', nombres)
      .set('apellidos', apellidos)

    return this.httpClient.get(this.apiUrl + '/paciente/nombesapellidos', {params});
  }

  public getTiposDocIdentidad(): Observable<any> {
    return this.httpClient.get(this.apiUrl + '/paciente/docidentidad/tipo');
  }


  public getSexos(): Observable<any> {
    return this.httpClient.get(this.apiUrl + '/paciente/sexo');
  }

  public getParentescos(): Observable<any> {
    return this.httpClient.get(this.apiUrl + '/paciente/parentesco');
  }

  public deletePaciente(idPaciente: number): Observable<any> {
    return this.httpClient.delete(this.apiUrl + '/paciente/' + idPaciente);
  }
}
