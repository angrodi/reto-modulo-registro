import { Pipe, PipeTransform } from '@angular/core';
import { Paciente } from 'src/app/data/types/Paciente';

@Pipe({
  name: 'fullname'
})
export class FullNamePipe implements PipeTransform {

  transform(paciente: Paciente): string {
    return paciente.apePaterno + " " + paciente.apeMaterno + " " + paciente.nombres;
  }

}