import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {  RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './singup/singup.component';

const appRoutes: Routes = [
  {
    path: '', children: [
      { path: 'login', component: LoginComponent },
      { path: 'singup', component: SingupComponent },
      { path: '**', redirectTo: 'login' }
    ]
  }
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(appRoutes)
  ]
})
export class RoutingLoginModule { }
