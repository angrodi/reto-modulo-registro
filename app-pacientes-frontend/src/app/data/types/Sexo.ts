export class Sexo {
  idSexo: number;
  descripcion: string;

  constructor(idSexo: number = 0, descripcion: string = '') {
    this.idSexo = idSexo;
    this.descripcion = descripcion;
  }
}