import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { RouterModule } from '@angular/router';

const layouts = [
  AdminLayoutComponent
]


@NgModule({
  declarations: [
    ...layouts
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    ...layouts
  ]
})
export class LayoutModule { }
