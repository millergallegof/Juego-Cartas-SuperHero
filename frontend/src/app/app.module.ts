import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModuleModule } from './app-routing-module.module';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';

import { AppComponent } from './app.component';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from '../environments/environment';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';

import { LoginComponent } from './component/login/login.component';
import { SingupComponent } from './component/singup/singup.component';
import { HomeComponentComponent } from './component/home-component/home-component.component';
import { FormsModule } from '@angular/forms';
import { ErrorComponentComponent } from './component/error-component/error-component.component';
import { AutenticacionServiceService } from './servicesAuth/autenticacion-service.service';
import { ListarTarjetasComponentComponent } from './component/listar-tarjetas-component/listar-tarjetas-component.component';
import { WaitingRoomComponetComponent } from './component/waiting-room-componet/waiting-room-componet.component';
import { GameOverComponent } from './component/game-over/game-over.component';
import { GanadorComponent } from './component/ganador/ganador.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SingupComponent,
    HomeComponentComponent,
    ErrorComponentComponent,
    ListarTarjetasComponentComponent,
    WaitingRoomComponetComponent,
    GameOverComponent,
    GanadorComponent,
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
    HttpClientModule,
    MatCardModule
  ],
  providers: [
    AutenticacionServiceService
  ],
  // providers: [
  //   AutenticacionServiceService
  // ],
  bootstrap: [AppComponent]
})
export class AppModule { }
