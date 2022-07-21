import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './component/singup/singup.component';
import { LoginComponent } from './component/login/login.component';
import { HomeComponentComponent } from './component/home-component/home-component.component';
import {  AngularFireAuthGuard,  AngularFireAuthGuardModule,  redirectLoggedInTo,  redirectUnauthorizedTo,} from '@angular/fire/compat/auth-guard';
import { ErrorComponentComponent } from './component/error-component/error-component.component';

//const redirectUnauthorizedToLogin = () => redirectUnauthorizedTo(['login']);
//const redirectLoggedInToDashboard = () => redirectLoggedInTo(['']);

const routesChildrens: Routes = [
  {
    path: '',
    component: HomeComponentComponent,
    canActivate: [AngularFireAuthGuard],
    //data: { authGuardPipe: redirectLoggedInToDashboard },
  },
  { path: 'singup', component: SingupComponent },
  { path: 'login', component: LoginComponent },
  {path: '', redirectTo:'/login', pathMatch: 'full'},
  { path: '**', component: ErrorComponentComponent },

]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routesChildrens)
  ],
  exports: [RouterModule]
})
export class AppRoutingModuleModule { }
