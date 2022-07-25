import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { ServiceHttpJugador } from 'src/app/service/http-service-jugador.service';
import { TableroComponentComponent } from '../tablero-component/tablero-component.component';


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

  tarjetas: Tarjeta[] = [];
  tarjetImgUser: any[] = [
    '../../../assets/img/icon-user.png'
  ]

  tarjetaEnviada: Tarjeta;
  disabledButton: boolean = false;
  informationTarjetas: Tarjeta[] = [];

  minutos: number;
  segundos: number;
  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpJugador: ServiceHttpJugador
  ) {
    this.minutos = 0;
    this.segundos = 10;
    setInterval(() => this.descontar(), 1000);

  }

  ngOnInit(): void {
    //  this.repatirBaraja();
    this.obtenerCartas();
  }

  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos = 10;
      if (--this.minutos < 0) {
        this.minutos = 0;
        this.segundos = 59;
      }
    }
  }

  repatirBaraja(): void {
    let idtemJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego
      .repartirBaraja(idtemJuego)
      .subscribe(() => {
        this.obtenerCartas();
      });
  }

  obtenerCartas(): void {
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);

    this.servicioHttpJuego
      .listarBarajaJugador(idJuego, { id: uid })
      .subscribe(data => {
        this.informationTarjetas = data.tarjetas;
        this.servicioHttpJugador.actualizarBaraja(uid, data)
          .subscribe(data => {
            console.log(data);
          })
      });
  }

  actualizarEstadoCarta(idTarjeta: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    console.log();
    this.servicioHttpJugador.traerTarjetaApostadaJugador(uid, idTarjeta)
    .subscribe(data => {
      this.tarjetaEnviada = data;
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




}
