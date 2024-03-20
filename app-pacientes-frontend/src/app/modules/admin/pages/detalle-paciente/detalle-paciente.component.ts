import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PacienteService } from 'src/app/data/services/paciente.service';
import { UbigeoService } from 'src/app/data/services/ubigeo.service';
import { Geonode } from 'src/app/data/types/Geonode';
import { Paciente } from 'src/app/data/types/Paciente';
import { PacienteAcompanante } from 'src/app/data/types/PacienteAcompanante';
import { Parentesco } from 'src/app/data/types/Parentesco';
import { Sexo } from 'src/app/data/types/Sexo';
import { TipoDocumentoIdentidad } from 'src/app/data/types/TipoDocumentoIdentidad';


@Component({
  selector: 'app-detalle-paciente',
  templateUrl: './detalle-paciente.component.html',
  styleUrls: ['./detalle-paciente.component.scss']
})
export class DetallePacienteComponent implements OnInit, AfterViewInit {

  @Input()
  paciente: Paciente = new Paciente();

  @Input()
  acompanante: PacienteAcompanante = new PacienteAcompanante();

  acompananteFormInitialValue: any;

  pacienteForm: FormGroup = this.fb.group({
    idTipoDocIdentidad: [this.paciente.tipoDocIdentidad.idTipoDocIdentidad, [ Validators.required ]],
    documentoIdentidad: [this.paciente.documentoIdentidad, [ Validators.required ]],
    apellidoPaterno   : [this.paciente.apePaterno, [ Validators.required ]],
    apellidoMaterno   : [this.paciente.apeMaterno, [ Validators.required ]],
    nombres           : [this.paciente.nombres, [ Validators.required ]],
    idSexo            : [this.paciente.sexo.idSexo, [ Validators.required ]],
    fechaNacimiento   : [this.paciente.fechaNacimiento, [ Validators.required ]],
    edad              : [{ value: '', disabled: true }, [ Validators.required ]],
    lugarNacimiento   : [this.paciente.lugarNacimiento, [ Validators.required ]],
    direccion         : [this.paciente.direccion, [ Validators.required ]],
    codigoDepartamento: [this.paciente.ubigeo.codigoDepartamento, [ Validators.required ]],
    codigoProvincia   : [this.paciente.ubigeo.codigoProvincia, [ Validators.required ]],
    codigoDistrito    : [this.paciente.ubigeo.codigoDistrito, [ Validators.required ]],
  });

  acompananteForm: FormGroup = this.fb.group({
    idTipoDocIdentidad: [this.acompanante.tipoDocIdentidad.idTipoDocIdentidad, [ Validators.required ]],
    documentoIdentidad: [this.acompanante.documentoIdentidad, [ Validators.required ]],
    apellidoPaterno   : [this.acompanante.apePaterno, [ Validators.required ]],
    apellidoMaterno   : [this.acompanante.apeMaterno, [ Validators.required ]],
    nombres           : [this.acompanante.nombres, [ Validators.required ]],
    fechaNacimiento   : [this.acompanante.fechaNacimiento, [ Validators.required ]],
    edad              : [{ value: '', disabled: true }, [ Validators.required ]],
    idParentesco      : [this.acompanante.parentesco.idParentesco, [ Validators.required ]],
    telefonoContacto  : [this.acompanante.telefonoContacto, [ Validators.required ]],
    direccion         : [this.acompanante.direccion, [ Validators.required ]],
    codigoDepartamento: [this.acompanante.ubigeo.codigoDepartamento, [ Validators.required ]],
    codigoProvincia   : [this.acompanante.ubigeo.codigoProvincia, [ Validators.required ]],
    codigoDistrito    : [this.acompanante.ubigeo.codigoDistrito, [ Validators.required ]],
  })

  tiposDocIdentidad: TipoDocumentoIdentidad[] = [];
  parentescos: Parentesco[] = [];
  sexos: Sexo[] = [];
  departamentos: Geonode[] = [];
  provincias: Geonode[] = [];
  distritos: Geonode[] = [];
  provinciasAcomp: Geonode[] = [];
  distritosAcomp: Geonode[] = [];

  maxDate = new Date();

  constructor(
    private fb: FormBuilder,
    private pacienteService: PacienteService,
    private ubigeoService: UbigeoService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.cargarTiposDocIdentidad();
    this.cargarDepartamentos(this.pacienteForm);
    this.cargarDepartamentos(this.acompananteForm);
    this.cargarSexos();
    this.cargarParentescos();
  }

  ngAfterViewInit(): void {
    this.acompananteFormInitialValue = this.acompananteForm.value;
  }

  guardar(): void {
    if (this.pacienteForm.invalid) return;
    if (JSON.stringify(this.acompananteForm.value) !== JSON.stringify(this.acompananteFormInitialValue) && this.acompananteForm.invalid) return;

    const fecNac = this.formatDate(this.pacienteForm.value.fechaNacimiento);
    const codUbigeo = this.pacienteForm.value.codigoDepartamento + this.pacienteForm.value.codigoProvincia + this.pacienteForm.value.codigoDistrito;

    this.paciente.tipoDocIdentidad.idTipoDocIdentidad = this.pacienteForm.value.idTipoDocIdentidad;
    this.paciente.documentoIdentidad = this.pacienteForm.value.documentoIdentidad;
    this.paciente.apePaterno = this.pacienteForm.value.apellidoPaterno;
    this.paciente.apeMaterno = this.pacienteForm.value.apellidoMaterno;
    this.paciente.nombres = this.pacienteForm.value.nombres;
    this.paciente.sexo.idSexo = this.pacienteForm.value.idSexo;
    this.paciente.fechaNacimiento = fecNac;
    this.paciente.lugarNacimiento = this.pacienteForm.value.lugarNacimiento;
    this.paciente.direccion = this.pacienteForm.value.direccion;
    this.paciente.ubigeo.codigo = codUbigeo;

    if (JSON.stringify(this.acompananteForm.value) !== JSON.stringify(this.acompananteFormInitialValue) && this.acompananteForm.valid) {
      const fecNacPA = this.formatDate(this.acompananteForm.value.fechaNacimiento);
      const codUbigeoPA = this.acompananteForm.value.codigoDepartamento + this.acompananteForm.value.codigoProvincia + this.acompananteForm.value.codigoDistrito;
  
      this.acompanante.tipoDocIdentidad.idTipoDocIdentidad = this.acompananteForm.value.idTipoDocIdentidad;
      this.acompanante.documentoIdentidad = this.acompananteForm.value.documentoIdentidad;
      this.acompanante.apePaterno = this.acompananteForm.value.apellidoPaterno;
      this.acompanante.apeMaterno = this.acompananteForm.value.apellidoMaterno;
      this.acompanante.nombres = this.acompananteForm.value.nombres;
      this.acompanante.fechaNacimiento = fecNacPA
      this.acompanante.parentesco.idParentesco = this.acompananteForm.value.idParentesco;
      this.acompanante.telefonoContacto = this.acompananteForm.value.telefonoContacto;
      this.acompanante.direccion = this.acompananteForm.value.direccion;
      this.acompanante.ubigeo.codigo = codUbigeoPA;

      this.pacienteService.createPaciente(this.paciente, this.acompanante)
      .subscribe(
        response => {
          if (response.status = 201) {
            this.toastr.success('Se registró correctamente');
            this.router.navigate(['/', 'ListarPaciente']);
          }
        }
      );
    } else [
      this.pacienteService.createPaciente(this.paciente, null)
      .subscribe(
        response => {
          if (response.status = 201) {
            this.toastr.success('Se registró correctamente');
            this.router.navigate(['/', 'ListarPaciente']);
          }
        }
      )
    ]
  }

  regresar(): void {
    this.router.navigate(['/', 'ListarPaciente']);
  }

  changeDate(): void {
    const edad = this.calcularEdad(this.pacienteForm.controls['fechaNacimiento'].value);
    this.pacienteForm.controls['edad'].setValue(edad);
  }

  calcularEdad(fechaNacimiento: Date) {
    let hoy = new Date();
    var edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    let mes = hoy.getMonth() - fechaNacimiento.getMonth();

    if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNacimiento.getDate())) {
        edad--;
    }

    return edad;
}


  isRequiredField(field: string, form: FormGroup): boolean {
    const formControl = form.get(field);
    return formControl?.errors?.['required'] && formControl?.touched;
  }

  cargarDepartamentos(form: FormGroup): void {
    form.controls['codigoProvincia'].disable();
    form.controls['codigoDistrito'].disable();

    this.ubigeoService.getDepartamentos()
      .subscribe(
        response => {
          this.departamentos = response.data;
        }
      );
  }

  cargarProvincias(form: FormGroup, flag: string): void {
    form.controls['codigoProvincia'].enable();
    form.controls['codigoDistrito'].disable();
    form.controls['codigoProvincia'].reset();
    form.controls['codigoDistrito'].reset();

    this.ubigeoService.getProvincias(form.controls['codigoDepartamento'].value)
      .subscribe(
        response => {
          if (flag === 'P') this.provincias = response.data;
          else this.provinciasAcomp = response.data;
        }
      );
  }

  cargarDistritos(form: FormGroup, flag: string): void {
    form.controls['codigoDistrito'].enable();
    form.controls['codigoDistrito'].reset();

    this.ubigeoService.getDistritos(form.controls['codigoDepartamento'].value, form.controls['codigoProvincia'].value)
      .subscribe(
        response => {
          if (flag === 'P') this.distritos = response.data;
          else this.distritosAcomp = response.data;
        }
      );
  }

  private cargarTiposDocIdentidad(): void {
    this.pacienteService.getTiposDocIdentidad()
      .subscribe(
        response => {
          this.tiposDocIdentidad = response.data;
        }
      );
  }

  private cargarSexos(): void {
    this.pacienteService.getSexos()
      .subscribe(
        response => {
          this.sexos = response.data;
        }
      );
  }

  private cargarParentescos(): void {
    this.pacienteService.getParentescos()
      .subscribe(
        response => {
          this.parentescos = response.data;
        }
      );
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const day = `0${date.getDate()}`.slice(-2);

    return `${year}-${month}-${day}`;
  }

}