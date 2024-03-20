import { Parentesco } from "./Parentesco";
import { Sexo } from "./Sexo";
import { TipoDocumentoIdentidad } from "./TipoDocumentoIdentidad";
import { Ubigeo } from "./Ubigeo";

export class PacienteAcompanante {
  idPacienteAcompanante: number;
  idPaciente: number;
  tipoDocIdentidad: TipoDocumentoIdentidad;
  documentoIdentidad: string;
  apePaterno: string;
  apeMaterno: string;
  nombres: string;
  fechaNacimiento: string;
  parentesco: Parentesco;
  telefonoContacto: string;
  direccion: string;
  ubigeo: Ubigeo;

  constructor(
    idPacienteAcompanante: number = 0,
    idPaciente: number = 0,
    tipoDocIdentidad: TipoDocumentoIdentidad = new TipoDocumentoIdentidad(),
    documentoIdentidad: string = '',
    apePaterno: string = '',
    apeMaterno: string = '',
    nombres: string = '',
    fechaNacimiento: string = '',
    parentesco: Parentesco = new Parentesco(),
    telefonoContacto: string = '',
    direccion: string = '',
    ubigeo: Ubigeo = new Ubigeo()
  ) {
    this.idPacienteAcompanante = idPacienteAcompanante;
    this.idPaciente = idPaciente;
    this.tipoDocIdentidad = tipoDocIdentidad;
    this.documentoIdentidad = documentoIdentidad;
    this.apePaterno = apePaterno;
    this.apeMaterno = apeMaterno;
    this.nombres = nombres;
    this.fechaNacimiento = fechaNacimiento;
    this.parentesco = parentesco;
    this.telefonoContacto = telefonoContacto;
    this.direccion = direccion;
    this.ubigeo = ubigeo;
  }
}