import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModuleModule } from './app-routing-module.module';
import { AppComponent } from './app.component';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from '../environments/environment';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';
import { AutenticacionServiceService } from './servicesAuth/autenticacion-service.service';
import { LoginComponent } from './component/login/login.component';
import { SingupComponent } from './component/singup/singup.component';
import { HomeComponentComponent } from './component/home-component/home-component.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SingupComponent,
    HomeComponentComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModuleModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireAuthModule,
    AngularFirestoreModule,
    AngularFireStorageModule,
    AngularFireDatabaseModule,
    FormsModule,
  ],
  providers: [
    AutenticacionServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
