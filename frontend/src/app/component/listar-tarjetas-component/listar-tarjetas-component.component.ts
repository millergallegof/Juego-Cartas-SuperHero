import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, retry, Subscription, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { ServiceHttpJugador } from 'src/app/service/http-service-jugador.service';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';
import { Route, Router } from '@angular/router';

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

  private subscription: Subscription;
  timeDifference: number;
  secondsToDday: number;
  minutesToDday: number;
  fechaActual: Date = new Date();
  fechaFinal: Date;
  milliSecondsInASecond: number = 1000;
  minutesInAnHour: number = 60;
  SecondsInAMinute: number = 60;

  // VARIABLES ARREGLO JUGADOR
  informationTarjetas: Tarjeta[] = [];
  tarjetaEnviada: Tarjeta;
  disabledButton: boolean = false;
  ronda: number = 1

  // VARIABLES TABLERO
  imagenTemporal = '../../../assets/img/revezCarta.jpg';
  tarjetasTablero: any[] = [];
  tarjetasTableroTemporal: any[] = [];
  interval: any;
  esVisible: boolean = true;

  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpJugador: ServiceHttpJugador,
    private servicioHttpTablero: ServiceHttpTablero,
    private router: Router
  ) {
    this.minutos = 0;
    this.segundos = 30;
    // this.interval = setInterval(() => this.asignarGanadorTimer(), 1000);
  }

  ngOnInit(): void {
    let { ganadorId } = JSON.parse(localStorage.getItem('tablero')!);
    setTimeout(() => {
      this.obtenerCartas();
    }, 500)
    if (ganadorId === null) {
      this.timerRonda()
    }
    this.mostrarCartasTablero()
  }
  // ----------------------------------------------------------------------------------------------------
  // TIMER RONDA
  // ----------------------------------------------------------------------------------------------------
  ngOnDestroy() {
    let rolJugador = JSON.parse(localStorage.getItem('rolJugador')!);
    if (rolJugador === "host") {
      this.elegirGanadorTablero()
    }
    this.subscription.unsubscribe();
    localStorage.setItem('limiteRonda', JSON.stringify(JSON.parse(localStorage.getItem('limiteRonda')!) + 60000))
    this.revisarGanadorJuego()
    this.disabledButton = false;
    this.ngOnInit()
  }

  timerRonda() {
    this.fechaFinal = new Date(JSON.parse(localStorage.getItem('limiteRonda')!))
    this.subscription = interval(1000)
      .subscribe(x => {
        this.getTimeDifference();
        if (this.secondsToDday <= 0) {
          this.voltearTarjetasTablero()
        }
        if (this.secondsToDday <= -5) {
          this.ngOnDestroy()
        }
      });
  }

  private getTimeDifference() {
    this.timeDifference = this.fechaFinal.getTime() - new Date().getTime();
    this.allocateTimeUnits(this.timeDifference);
  }

  private allocateTimeUnits(timeDifference: number) {
    this.secondsToDday = Math.floor((timeDifference) / (this.milliSecondsInASecond) % this.SecondsInAMinute);
    this.minutesToDday = Math.floor((timeDifference) / (this.milliSecondsInASecond * this.minutesInAnHour) % this.SecondsInAMinute);
  }

  // ----------------------------------------------------------------------------------------------------
  // LISTAR TARJETAS JUGADOR
  // ----------------------------------------------------------------------------------------------------
  obtenerCartas(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
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
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
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
  // FUNCIONALIDADES JUEGO
  // ----------------------------------------------------------------------------------------------------
  aumentarRonda(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego.aumentaRonda(idJuego)
      .subscribe(data => {
        this.ronda = data.ronda
        this.ngOnInit()
      });
  }

  revisarGanadorJuego(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    this.servicioHttpJuego.finalizarJuego(idJuego)
      .subscribe(data => {
        if (data.ganador === "") {
          console.log("Nadie Gano" + data)
          this.aumentarRonda()
        } else {
          localStorage.setItem('informacionJuego', JSON.stringify({ idJuego: data.id, ganador: data.ganador }))
          setTimeout(() => {
            if (data.ganador == uid) {
              this.router.navigate(['/ganador'])
            } else {
              this.router.navigate(['/gameover'])
            }
          }, 500)
        }
      })
  }

  // ----------------------------------------------------------------------------------------------------
  // TABLERO
  // ----------------------------------------------------------------------------------------------------
  asignarGanadorTimer(): void {
    let { ganadorId } = JSON.parse(localStorage.getItem('tablero')!);


    if (ganadorId !== null) {
      clearInterval(this.interval)
    } else {
      this.segundos = 10
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
        this.mostrarCartasTablero()
      })
  }

  elegirGanadorTablero(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero.elegirGanador(id)
      .subscribe(tablero => {
        localStorage.setItem('tablero', JSON.stringify(tablero))
        this.servicioHttpTablero.recibirTarjetas(id)
          .subscribe(tarjetasGanador => {
            let { ganadorId } = JSON.parse(localStorage.getItem('tablero')!);
            this.servicioHttpJuego.actuaLizarBarajaGanadorRonda(idJuego, { idJugador: ganadorId, tarjetas: tarjetasGanador })
              .subscribe(juego => {
                this.eliminarTarjetasTablero()
              })
          })
      })
  }

  voltearTarjetasTablero(): void {
    this.esVisible = false
  }

  eliminarTarjetasTablero(): void {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero.eliminarTarjetas(id)
      .subscribe(() => {
        this.ngOnInit()
      })
  }

}
