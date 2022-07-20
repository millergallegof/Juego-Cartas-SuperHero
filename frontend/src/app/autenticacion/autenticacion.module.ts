import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RoutingLoginModule } from '../autenticacion/routing-login.module';

import { AutenticacionServiceService } from '../autenticacion/servicesAuth/autenticacion-service.service';
import { SingupComponent } from './singup/singup.component';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';
import { environment } from '../../environments/environment';
// import { AngularFireModule } from '@angular/fire/compat';

@NgModule({
  declarations: [
    LoginComponent,
    SingupComponent
  ],
  imports: [
    CommonModule,
    RoutingLoginModule,
    AngularFireAuthModule,
    AngularFirestoreModule,
    // AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule,
    AngularFireDatabaseModule,
  ],
  providers: [
    AutenticacionServiceService
  ]

})
export class AutenticacionModule { }
