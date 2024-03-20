import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PacienteService } from 'src/app/data/services/paciente.service';
import { Paciente } from 'src/app/data/types/Paciente';
import { TipoDocumentoIdentidad } from 'src/app/data/types/TipoDocumentoIdentidad';


@Component({
  selector: 'app-buscar-paciente',
  templateUrl: './buscar-paciente.component.html',
  styleUrls: ['./buscar-paciente.component.scss']
})
export class BuscarPacienteComponent implements OnInit, AfterViewInit {

  docForm: FormGroup = this.fb.group({
    tipoDocIdentidad: ['', [ Validators.required ]],
    docIdentidad    : ['', [ Validators.required ]]
  })

  nameForm: FormGroup = this.fb.group({
    nombres  : ['', [ Validators.maxLength(40) ]],
    apellidos: ['', [ Validators.maxLength(40) ]],
  }, { validators: [atLeastOneValidator] })

  displayedColumns: string[] = ['nombre', 'tipoDoc', 'docIdentidad', 'fechaNac', 'estado', 'editar', 'eliminar'];
  dataSource = new MatTableDataSource<Paciente>();
  pageSize: number = 10;
  tiposDocIdentidad: TipoDocumentoIdentidad[] = [];

  searchOption: string = 'doc';

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static:true }) sort!: MatSort;

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private pacienteService: PacienteService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.cargarPacientes();
    this.cargarTiposDocIdentidad();
    this.changeSearchOption();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  
  nuevoPaciente(): void {
    this.router.navigate(['/', 'CrearPaciente']);
  }

  editUser(paciente: Paciente): void {
  }

  deleteUser(idPaciente: number): void {
    this.pacienteService.deletePaciente(idPaciente)
      .subscribe({
        next: response => {
          this.toastr.success('Se eliminó correctamente');
          this.cargarPacientes();
        }
      });
  }

  buscarPaciente(): void {
    if (this.searchOption === 'doc') {
      if (this.docForm.invalid) {
        this.docForm.markAllAsTouched();
        return;
      };

      const { tipoDocIdentidad, docIdentidad } = this.docForm.value;
      this.pacienteService.getPacienteByDocIdentidad(tipoDocIdentidad, docIdentidad)
        .subscribe({
          next: response => {
            this.dataSource.data = response.data;
          },
          error: error => {
            if (error.status === 404) {
              this.dataSource.data = [];
            }
          }
        });
    } else {
      if (this.nameForm.invalid) {
        this.nameForm.markAllAsTouched();
        return;
      };

      const { nombres, apellidos } = this.nameForm.value;
      this.pacienteService.getPacienteByNombresAndApellidos(nombres, apellidos)
        .subscribe({
          next: response => {
            this.dataSource.data = response.data;
          }
        });
    }
  }

  limpiar(): void {
    this.docForm.reset();
    this.nameForm.reset();
  }

  changeSearchOption(): void {
    if (this.searchOption === 'doc') {
      this.docForm.enable();
      this.nameForm.disable();
    } else {
      this.docForm.disable();
      this.nameForm.enable();
    }
  }

  save(): void {}

  isRequiredField(field: string, form: FormGroup): boolean {
    const formControl = form.get(field);
    return formControl?.errors?.['required'] && formControl?.touched;
  }

  private cargarPacientes(): void {
    this.pacienteService.getPacientes()
      .subscribe(
        response => {
          this.dataSource.data = response.data;
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

}

export const atLeastOneValidator: ValidatorFn = (
  control: AbstractControl
): ValidationErrors | null => {
  if (
    control.get('nombres')?.value != '' ||
    control.get('apellidos')?.value != ''
  ) {
    return null;
  } else {
    return { atLeastone: true };
  }
};