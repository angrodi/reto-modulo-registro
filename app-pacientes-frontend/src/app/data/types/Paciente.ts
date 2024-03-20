import { Sexo } from "./Sexo";
import { TipoDocumentoIdentidad } from "./TipoDocumentoIdentidad";
import { Ubigeo } from "./Ubigeo";

export class Paciente {
  idPaciente: number;
  tipoDocIdentidad: TipoDocumentoIdentidad;
  documentoIdentidad: string;
  apePaterno: string;
  apeMaterno: string;
  nombres: string;
  sexo: Sexo;
  fechaNacimiento: string;
  lugarNacimiento: string;
  direccion: string;
  ubigeo: Ubigeo;

  constructor(
    idPaciente: number = 0,
    tipoDocIdentidad: TipoDocumentoIdentidad = new TipoDocumentoIdentidad(),
    documentoIdentidad: string = '',
    apePaterno: string = '',
    apeMaterno: string = '',
    nombres: string = '',
    sexo: Sexo = new Sexo(),
    fechaNacimiento: string = '',
    lugarNacimiento: string = '',
    direccion: string = '',
    ubigeo: Ubigeo = new Ubigeo()
  ) {
    this.idPaciente = idPaciente;
    this.tipoDocIdentidad = tipoDocIdentidad;
    this.documentoIdentidad = documentoIdentidad;
    this.apePaterno = apePaterno;
    this.apeMaterno = apeMaterno;
    this.nombres = nombres;
    this.sexo = sexo;
    this.fechaNacimiento = fechaNacimiento;
    this.lugarNacimiento = lugarNacimiento;
    this.direccion = direccion;
    this.ubigeo = ubigeo;
  }
}