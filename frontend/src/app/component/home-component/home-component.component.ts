import { Component, OnInit } from '@angular/core';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Tarjeta } from '../../models/Itarjetas';
import { Jugador } from '../../models/Ijugador';
import { ServiceHttpJugador } from '../../service/http-service-jugador.service';
import { Juego } from '../../models/Ijuego';
import { ServiceHttpTablero } from '../../service/http-service-tablero.service';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  jugadores: Jugador[] = [];
  juegos: Juego[] = [];
  idTablero: string = "";
  idJuego: string = "";
  idHost: string = "";

  constructor(
    public autenticacionService: AutenticacionServiceService,
    public servicioHttpJugador: ServiceHttpJugador,
    public servicioHttpJuego: ServiceHttJuego,
    public servicioHttpTablero: ServiceHttpTablero) {
  }

  ngOnInit(): void {
    this.listarJuegos()
    this.comenzarJuego("62dda5b016bc021316dc0032")
  }

  listarJuegos(): void {
    this.servicioHttpJuego
      .listarJuego()
      .subscribe(data => this.juegos.push(data));
  }

  crearSala(nickName: string): void {
    this.crearJuego()
    this.crearJugador(nickName);
    this.crearTablero();
    this.comenzarJuego(this.idJuego)


  }

  crearJugador(nickName: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    this.servicioHttpJugador
      .crearJugador
      ({ id: uid, nickName: nickName, puntos: 0, baraja: null, estado: true })
      .subscribe(data => {
        this.jugadores.push(data)
        this.idHost = data.id
      });

    this.servicioHttpJugador.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then(() => console.log('Actualizado'))
      .catch(err => console.log(err));
  }

  crearJuego(): void {
    let tarjetas: Tarjeta[] = [];
    this.servicioHttpJuego
      .crearJuego({ id: null, ronda: 1, mazoJuego: tarjetas, ganador: "", tableroId: "", jugadores: this.jugadores })
      .subscribe(data => {
        console.log(data);
        this.idJuego = data.id!
      })
  }

  crearTablero(): void {
    this.servicioHttpTablero
      .crearTablero({ id: null, apuesta: null, ganadorId: "", tiempo: 30 })
      .subscribe(data => this.idTablero = data.id!);
  }

  comenzarJuego(idJuego: string): void {
    let tableroJugadores = {
      jugadores: this.jugadores,
      idTablero: this.idTablero
    }
    this.servicioHttpJuego
      .comenzarJuego(idJuego, tableroJugadores)
      .subscribe(data => this.juegos.push(data));
  }
}
