import { Component, OnInit } from '@angular/core';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';
import { HTTPServiceGameService } from '../../service/httpservice-game.service';
import { Tarjeta } from '../../models/Itarjetas';
import { Jugador } from '../../models/Ijugador';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  constructor(
    public autenticacionService: AutenticacionServiceService,
    public peticionesApi: HTTPServiceGameService) {
  }

  ngOnInit(): void {}


  aceptarJuego(nickName: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let tarjetas: Tarjeta[] = [];
    let jugadores: Jugador[] = [];
    let jugador = this.peticionesApi.crearJugador({ id: uid, nickName: nickName, puntos: 0, baraja: null, estado: true }).subscribe();
    this.peticionesApi.crearJuego({ ronda: 1, mazoJuego: tarjetas, ganador: "", tableroId: "", jugadores: jugadores }).subscribe();
    console.log(this.peticionesApi.crearBaraja().subscribe());
    
    this.peticionesApi.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then(() => console.log('Actualizado'))
      .catch(err => console.log(err));
  }
}
