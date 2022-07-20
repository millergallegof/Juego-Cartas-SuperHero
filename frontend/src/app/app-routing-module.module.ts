import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './component/singup/singup.component';
import { LoginComponent } from './component/login/login.component';

const routesChildrens: Routes = [
  // {
  //   path: 'auth', 
  //   loadChildren: () => import ('./autenticacion/autenticacion.module').then((m) => m.AutenticacionModule)
  // },
  { path: 'singup', component: SingupComponent },
  { path: 'login', component: LoginComponent },
  {
    path: '**',
    redirectTo: 'auth'
  }
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
