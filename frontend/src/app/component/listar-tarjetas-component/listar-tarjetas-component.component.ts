import { Component, OnInit } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { ServiceHttpJugador } from 'src/app/service/http-service-jugador.service';

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

  cartasCampo: any[] = [];
  disabledButton: boolean = false;
  informationTarjeta: Tarjeta[] = [];

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
        this.informationTarjeta = data.tarjetas;
        this.servicioHttpJugador.actualizarBaraja(uid, data)
          .subscribe(data => {
            console.log(data);
          })
      });
  }

  actualizarEstadoCarta(idTarjeta: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let idJuego = JSON.parse(localStorage.getItem('informacionJuego')!);

    this.servicioHttpJugador.apostarTarjeta(uid, idTarjeta)
      .subscribe(data => {
        this.servicioHttpJuego.actualizarBarajaJugador(idJuego, {idJugador: uid, baraja: data.baraja!})
        .subscribe(data => { data})
      });

    this.eliminarCartaBaraja(idTarjeta);
    this.agregarCartaCampo(idTarjeta);
  }

  eliminarCartaBaraja(idTarjeta: string): void {
    let temporal = this.informationTarjeta.filter(element => element.id !== idTarjeta);
    this.informationTarjeta = temporal;
  }

  agregarCartaCampo(idTarjeta: string): void {
    let temporal: formatemporal;
    temporal = this.informationTarjeta.filter(element => element.id == idTarjeta);
    this.disabledButton = true;
    temporal['link'] = '../../.././assets/img/revezCarta.jpg';
    this.cartasCampo.push(temporal);
  }


}
