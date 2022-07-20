import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RoutingLoginModule } from '../autenticacion/routing-login.module';

import { AutenticacionServiceService } from '../autenticacion/servicesAuth/autenticacion-service.service';
import { SingupComponent } from './singup/singup.component';


@NgModule({
  declarations: [
    LoginComponent,
    SingupComponent
  ],
  imports: [
    CommonModule,
    RoutingLoginModule,

  ],
 


})
export class AutenticacionModule { }
