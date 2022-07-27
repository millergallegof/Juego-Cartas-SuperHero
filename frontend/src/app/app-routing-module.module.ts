import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './component/singup/singup.component';
import { LoginComponent } from './component/login/login.component';
import { HomeComponentComponent } from './component/home-component/home-component.component';
import { AngularFireAuthGuard, } from '@angular/fire/compat/auth-guard';
import { ErrorComponentComponent } from './component/error-component/error-component.component';
import { ListarTarjetasComponentComponent } from './component/listar-tarjetas-component/listar-tarjetas-component.component';
import { WaitingRoomComponetComponent } from './component/waiting-room-componet/waiting-room-componet.component';
import { GameOverComponent } from './component/game-over/game-over.component';
import { GanadorComponent } from './component/ganador/ganador.component';


const routesChildrens: Routes = [
  {
    path: '',
    component: HomeComponentComponent,
    canActivate: [AngularFireAuthGuard],
  },
  {
    path: 'juego',
    component: ListarTarjetasComponentComponent,
    canActivate: [AngularFireAuthGuard]
  },
  {
    path: 'salaespera',
    component: WaitingRoomComponetComponent,
    canActivate: [AngularFireAuthGuard]
  },
  {
    path: 'singup', component: SingupComponent,
    canActivate: [AngularFireAuthGuard]
  },

  {
    path: 'login', component: LoginComponent
  },

  {
    path: 'gameover', component: GameOverComponent,
    canActivate: [AngularFireAuthGuard]
  },

  {
    path: 'ganador', component: GanadorComponent,
    canActivate: [AngularFireAuthGuard]
  },

  { path: '**', redirectTo: '/login', pathMatch: 'full' },

  {
    path: '**', component: ErrorComponentComponent,
    canActivate: [AngularFireAuthGuard]
  },


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
