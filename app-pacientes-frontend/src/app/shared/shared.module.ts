import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullNamePipe } from './pipes/fullname.pipe';
import { OnlyNumberDirective } from './directives/only-number.directive';
import { OnlyNumberComaDirective } from './directives/only-number-coma.directive';



@NgModule({
  declarations: [
    FullNamePipe,
    OnlyNumberDirective,
    OnlyNumberComaDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FullNamePipe,
    OnlyNumberDirective,
    OnlyNumberComaDirective
  ]
})
export class SharedModule { }
