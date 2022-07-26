import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { ServiceHttpJugador } from 'src/app/service/http-service-jugador.service';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';

@Component({
  selector: 'app-listar-tarjetas-component',
  templateUrl: './listar-tarjetas-component.component.html',
  styleUrls: ['./listar-tarjetas-component.component.css']
})
export class ListarTarjetasComponentComponent implements OnInit {
  tarjetImgUser: any[] = ['../../../assets/img/icon-user.png']
  // VARIABLES TIMER
  minutos: number;
  segundos: number;

  // VARIABLES ARREGLO JUGADOR
  informationTarjetas: Tarjeta[] = [];
  tarjetaEnviada: Tarjeta;
  disabledButton: boolean = false;

  // VARIABLES TABLERO
  imagenTemporal = '../../../assets/img/revezCarta.jpg';
  tarjetasTablero: any[] = [];
  tarjetasTableroTemporal: any[] = [];
  interval: any;
  ronda: number = 1;

  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpJugador: ServiceHttpJugador,
    private servicioHttpTablero: ServiceHttpTablero
  ) {
    this.minutos = 0;
    this.segundos = 10;
    this.interval = setInterval(() => this.asignarGanadorTimer(), 1000);
  }

  ngOnInit(): void {
    this.obtenerCartas();
    this.mostrarCartasTablero()

  }

  // ----------------------------------------------------------------------------------------------------
  // LISTAR TARJETAS JUGADOR
  // ----------------------------------------------------------------------------------------------------

  obtenerCartas(): void {
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    this.servicioHttpJuego
      .listarBarajaJugador(idJuego, { id: uid })
      .subscribe(data => {
        this.informationTarjetas = data.tarjetas;
        this.servicioHttpJugador.actualizarBaraja(uid, data)
          .subscribe(data => {
            data;
          })
      });
  }

  actualizarEstadoCarta(idTarjeta: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJugador.traerTarjetaApostadaJugador(uid, idTarjeta)
      .subscribe(data => {
        this.enviarTarjetaTablero(data)
      });
    this.servicioHttpJugador.apostarTarjeta(uid, idTarjeta)
      .subscribe(jugador => {
        this.servicioHttpJuego.actualizarBarajaJugador(idJuego, { idJugador: uid, baraja: jugador.baraja! })
          .subscribe(juego => { juego })
        this.ngOnInit()
      });
    this.eliminarCartaBaraja(idTarjeta);
  }

  eliminarCartaBaraja(idTarjeta: string): void {
    let temporal = this.informationTarjetas.filter(element => element.id !== idTarjeta);
    this.informationTarjetas = temporal;
    this.disabledButton = true;
  }

  // ----------------------------------------------------------------------------------------------------
  // TABLERO
  // ----------------------------------------------------------------------------------------------------
  asignarGanadorTimer(): void {
    let { ganadorId } = JSON.parse(localStorage.getItem('tablero')!);
    if (--this.segundos < 0) {
      if (ganadorId !== null) {
        
        this.aumentarRonda()
        this.elegirGanadorTablero()
        clearInterval(this.interval)
      } else {
        console.log("hola");
        this.segundos = 10
      }
    }
  }

  mostrarCartasTablero() {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero
      .recibirTarjetas(id)
      .subscribe(data => {
        this.tarjetasTablero = data
      })
  };

  enviarTarjetaTablero(tarjeta: Tarjeta): void {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let jugadorTarjeta = new Map()
    jugadorTarjeta.set(uid, tarjeta)
    this.servicioHttpTablero.enviarTarjeta(id, jugadorTarjeta)
      .subscribe(data => {
        console.log(data);
        this.ngOnInit()
      })
  }

  elegirGanadorTablero(): void {
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    let { ganadorId } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero.elegirGanador(id)
      .subscribe(tablero => {
        localStorage.setItem('tablero', JSON.stringify(tablero))
        this.servicioHttpTablero.recibirTarjetas(id)
          .subscribe(tarjetasGanador => {
            this.servicioHttpJuego.actuaLizarBarajaGanadorRonda(idJuego, { idJugador: ganadorId, tarjetas: tarjetasGanador })
              .subscribe(juego => juego)
          })
      })
  }

  aumentarRonda() {
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego.aumentaRonda(idJuego)
      .subscribe(data => {
        this.ronda = data.ronda
        this.ngOnInit()
      });
  }

}
