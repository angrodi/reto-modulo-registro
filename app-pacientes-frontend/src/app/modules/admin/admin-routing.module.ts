import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuscarPacienteComponent } from './pages/buscar-paciente/buscar-paciente.component';
import { DetallePacienteComponent } from './pages/detalle-paciente/detalle-paciente.component';

const routes: Routes = [
  {
    path: 'ListarPaciente',
    component: BuscarPacienteComponent
  },
  {
    path: 'CrearPaciente',
    component: DetallePacienteComponent
  },
  {
    path: '**',
    redirectTo: 'ListarPaciente'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
