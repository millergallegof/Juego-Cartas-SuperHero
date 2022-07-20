import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutenticacionModule } from './autenticacion/autenticacion.module';
import { RoutingLoginModule } from './autenticacion/routing-login.module';
import { RouterModule } from '@angular/router';


const appRoutes = [
  {path: 'autenticacion', loadChildre: () => {
    import ('./autenticacion/autenticacion.module').then((m) => m.AutenticacionModule)
  }}
  
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModuleModule { }
