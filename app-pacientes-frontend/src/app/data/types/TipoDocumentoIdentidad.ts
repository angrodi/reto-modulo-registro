export class TipoDocumentoIdentidad {
  idTipoDocIdentidad: number;
  descripcion: string;
  codigoIeds: string;

  constructor(
    idTipoDocIdentidad: number = 0,
    descripcion: string = '',
    codigoIeds: string = ''
  ) {
    this.idTipoDocIdentidad = idTipoDocIdentidad;
    this.descripcion = descripcion;
    this.codigoIeds = codigoIeds;
  }
}