import { Component, OnInit } from '@angular/core';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';
import { HTTPServiceGameService } from '../../service/http-service-juego.service';
import { Tarjeta } from '../../models/Itarjetas';
import { Jugador } from '../../models/Ijugador';
import { ServicioJugadorService } from 'src/app/service/http-service-jugador.service';
import { ServicioJuegoService } from '../../service/http-servicio-juego.service';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  jugador: Jugador[] = [
    {
      id: "a0GqjysrmRMtJk9aCdGvR2Mldtz4",
      nickName: "s",
      puntos: 0,
      baraja: null,
      estado: true
    }
  ];
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


    this.peticionesJuego.crearJuego({ ronda: 1, mazoJuego: tarjetas, ganador: "", tableroId: "", jugadores: this.jugador }).subscribe();
    console.log(this.peticionesApi.crearBaraja().subscribe());

    this.peticionesApi.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then(() => console.log('Actualizado'))
      .catch(err => console.log(err));
  }
}
