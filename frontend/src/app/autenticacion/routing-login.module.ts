import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {  RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {
    path: '', children: [
      { path: 'login', component: LoginComponent },
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
