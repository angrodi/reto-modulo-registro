export class Ubigeo {
  codigo: string;
  descDepartamento: string;
  descProvincia: string;
  descDistrito: string;
  codigoDepartamento: string;
  codigoProvincia: string;
  codigoDistrito: string;

  constructor(
    codigo: string = '',
    descDepartamento: string = '',
    descProvincia: string = '',
    descDistrito: string = '',
    codigoDepartamento: string = '',
    codigoProvincia: string = '',
    codigoDistrito: string = ''
  ) {
    this.codigo = codigo;
    this.descDepartamento = descDepartamento;
    this.descProvincia = descProvincia;
    this.descDistrito = descDistrito;
    this.codigoDepartamento = codigoDepartamento;
    this.codigoProvincia = codigoProvincia;
    this.codigoDistrito = codigoDistrito;
  }
}