import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { ServiceHttpJugador } from 'src/app/service/http-service-jugador.service';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';


type formatemporal = {
  id?: string;
  [link: string]: any;
  nombre?: string;
  poder?: number;
}

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

  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpJugador: ServiceHttpJugador,
    private servicioHttpTablero: ServiceHttpTablero
  ) {
    this.minutos = 0;
    this.segundos = 10;
    setInterval(() => this.descontar(), 1000);
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
  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos = 10;
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


  agregarCartaTablero(idTarjeta: string): void {
    let temporal: formatemporal;
    temporal = this.tarjetasTablero.filter(element => element.id == idTarjeta);

    this.tarjetasTableroTemporal.push(temporal);
  }

  enviarTarjetaTablero(tarjeta: Tarjeta) {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let jugadorTarjeta = new Map()
    jugadorTarjeta.set(uid, tarjeta)
    this.servicioHttpTablero.enviarTarjeta(id, jugadorTarjeta)
      .subscribe(data => {
        console.log(data);
        this.mostrarCartasTablero()
      })

  }

}
