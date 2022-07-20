import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './component/singup/singup.component';
import { LoginComponent } from './component/login/login.component';

const routesChildrens: Routes = [
  { path: 'singup', component: SingupComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'login' }
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
