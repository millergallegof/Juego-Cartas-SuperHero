import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModuleModule } from './app-routing-module.module';
import { AppComponent } from './app.component';
import { AutenticacionModule } from './autenticacion/autenticacion.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModuleModule,
    AutenticacionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
