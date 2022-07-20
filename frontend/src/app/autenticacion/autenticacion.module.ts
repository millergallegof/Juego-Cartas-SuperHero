import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RoutingLoginModule } from '../autenticacion/routing-login.module';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from 'src/environments/environment';
import { AutenticacionServiceService } from '../autenticacion/servicesAuth/autenticacion-service.service';



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    AngularFireModule.initializeApp(environment.firebaseConfig),
    CommonModule,
    RoutingLoginModule
  ],
  providers: [
    AutenticacionServiceService
  ]


})
export class AutenticacionModule { }
