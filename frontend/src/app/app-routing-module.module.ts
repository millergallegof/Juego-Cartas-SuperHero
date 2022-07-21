import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './component/singup/singup.component';
import { LoginComponent } from './component/login/login.component';
import { HomeComponentComponent } from './component/home-component/home-component.component';

const routesChildrens: Routes = [
  { path: '', component: HomeComponentComponent },
  { path: 'singup', component: SingupComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'auth' }
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
