export class Parentesco {
  idParentesco: number;
  parentesco: string;

  constructor(
    idParentesco: number = 0,
    parentesco: string = ''
  ) {
    this.idParentesco = idParentesco;
    this.parentesco = parentesco;
  }
}
