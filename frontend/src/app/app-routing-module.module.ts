import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';

const routesChildrens: Routes = [
  {
    path: 'auth', 
    loadChildren: () => import ('./autenticacion/autenticacion.module').then((m) => m.AutenticacionModule)
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
