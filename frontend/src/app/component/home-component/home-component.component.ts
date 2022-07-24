import { Component, OnInit } from '@angular/core';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';
import { HTTPServiceGameService } from '../../service/httpservice-game.service';
import { Tarjeta } from '../../models/Itarjetas';
import { Jugador } from '../../models/Ijugador';
import { ServicioJugadorService } from 'src/app/service/servicio-jugador.service';
import { ServicioJuegoService } from '../../service/servicio-juego.service';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  jugador: Jugador[] = [];
  constructor(
    public autenticacionService: AutenticacionServiceService,
    public peticionesApi: ServicioJugadorService,
    public peticionesJuego: ServicioJuegoService) {
  }

  ngOnInit(): void { }


  aceptarJuego(nickName: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let tarjetas: Tarjeta[] = [];
    let jugadores: Jugador[] = [];
    this.peticionesApi
      .crearJugador
      ({ id: uid, nickName: nickName, puntos: 0, baraja: null, estado: true })
      .subscribe(retorno => this.jugador.push(retorno));


    this.peticionesJuego
    .crearJuego({ ronda: 1, mazoJuego: tarjetas, ganador: "", tableroId: "", jugadores: this.jugador })
    .subscribe();
    console.log(this.peticionesApi.crearBaraja().subscribe());

    this.peticionesApi.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then(() => console.log('Actualizado'))
      .catch(err => console.log(err));
  }
}
