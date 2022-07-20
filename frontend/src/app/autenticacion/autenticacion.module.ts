import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RoutingLoginModule } from '../autenticacion/routing-login.module';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    RoutingLoginModule,
  ]
})
export class AutenticacionModule { }
