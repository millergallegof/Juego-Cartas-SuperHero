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
  ) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.obtenerCartas();
    }, 800)
    this.timerRonda()
    this.esVisible = true;
    this.mostrarCartasTablero()
  }

  // ----------------------------------------------------------------------------------------------------
  // TIMER RONDA
  // ----------------------------------------------------------------------------------------------------
  ngOnDestroy() {
    // se dessuscribe del observable
    this.subscription.unsubscribe()
    let rolJugador = JSON.parse(localStorage.getItem('rolJugador')!);
    let limiteRonda = JSON.parse(localStorage.getItem('limiteRonda')!);
    if (rolJugador === "host") {
      this.revisarGanadorJuego()
    }
    this.elegirGanadorTablero()
    localStorage.setItem('limiteRonda', JSON.stringify(limiteRonda + 30000))
    this.disabledButton = false;
    setTimeout(() => {
      this.obtenerGanadorJuego()
    }, 1000)
  }

  timerRonda() {
    this.fechaFinal = new Date(JSON.parse(localStorage.getItem('limiteRonda')!))
    // crea un interval que va de 1 segundo en 1 segundo
    this.subscription = interval(1000)
      .subscribe(x => {
        // trae la diferencia entre la fecha inicial y el limite
        this.getTimeDifference();
        // si el tiempo llega a 0 se ejecuta esto
        if (this.secondsToDday == 0) {
          this.mostrarCartasTablero()
          this.voltearTarjetasTablero()
        }
        // si el tiempo es menor a -5 se destruye el interval
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
  // OK
  obtenerCartas(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    // lista la baraja de cartas del jugador de la BD Juego
    this.servicioHttpJuego.listarBarajaJugador(idJuego, { id: uid }).pipe(retry(2))
      .subscribe(data => {
        // actualiza arreglo que muestra cartas del jugador
        this.informationTarjetas = data.tarjetas;
        // actualiza la baraja del jugador en BD Jugador
        this.servicioHttpJugador.actualizarBaraja(uid, data).subscribe()
      });
  }
  //Apostar Tarjeta
  // OK
  actualizarEstadoCarta(idTarjeta: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    // trae la tarjeta apostada, desde la BD Jugador
    this.servicioHttpJugador.traerTarjetaApostadaJugador(uid, idTarjeta)
      .subscribe(data => {
        // envia la tarjeta a la BD Tablero
        this.enviarTarjetaTablero(data)
      });
    // quita de la BD Jugador la tarjeta que se esta apostando
    this.servicioHttpJugador.apostarTarjeta(uid, idTarjeta)
      .subscribe(jugador => {
        // elimina la carta del arreglo que muestra la baraja del jugador
        this.eliminarCartaBaraja(idTarjeta);
        // Quita de la BD Juego la tarjeta que fue apostada
        this.servicioHttpJuego.actualizarBarajaJugador(idJuego, { idJugador: uid, baraja: jugador.baraja! })
          .subscribe(juego => {
            // muestra las cartas del tablero
            this.mostrarCartasTablero()
          });
      });


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
    // aumenta ronda BD Juego
    this.servicioHttpJuego.aumentaRonda(idJuego)
      .subscribe(data => {
        this.ronda = data.ronda
      });
  }

  revisarGanadorJuego(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    // verifica que el juego tenga un ganador en la BD Juego
    this.servicioHttpJuego.finalizarJuego(idJuego)
      .subscribe()
  }

  obtenerGanadorJuego(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    // obtiene el juego de la BD Juego
    this.servicioHttpJuego.obtenerJuego(idJuego)
      .subscribe(juego => {
        // evalua si existe un ganador en este juego
        if (juego.ganador === "") {
          console.log("Nadie Gano" + juego)
          this.aumentarRonda()
        } else {
          localStorage.setItem('informacionJuego', JSON.stringify({ idJuego: juego.id, ganador: juego.ganador }))
          setTimeout(() => {
            if (juego.ganador == uid) {
              this.router.navigate(['/ganador'])
            } else {
              this.router.navigate(['/gameover'])
            }
          }, 500)
        }
        // me trae las fucnionalidades del onInit
        this.ngOnInit()
      })
  }

  // ----------------------------------------------------------------------------------------------------
  // TABLERO
  // ----------------------------------------------------------------------------------------------------
  mostrarCartasTablero() {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero
      .enviarTarjetasTablero(id).pipe(retry(2))
      .subscribe(data => {
        this.tarjetasTablero = data
      })
  };

  // OK
  enviarTarjetaTablero(tarjeta: Tarjeta): void {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let jugadorTarjeta = new Map()
    jugadorTarjeta.set(uid, tarjeta)
    // ingresa las tarjetas en juego a la BD del Tablero
    this.servicioHttpTablero.recibirTarjetasTablero(id, jugadorTarjeta)
      .subscribe(data => {
        // muestra las cartas actuales que estan en el tablero
        this.mostrarCartasTablero()
      })
  }

  // OK
  elegirGanadorTablero(): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    // elije el ganador del tablero
    this.servicioHttpTablero.elegirGanador(id)
      .subscribe(tablero => {
        // envias las tarjetas que estaban en juego en el tablero
        this.servicioHttpTablero.enviarTarjetasTablero(id)
          .subscribe(tarjetasGanador => {
            // evalua si este jugador es el ganador y actualiza las tarjetas
            if (uid === tablero.ganadorId) {
              // actualiza tarjetas BD Jugador
              this.servicioHttpJugador.actualizarTarjetasJugador(tablero.ganadorId, tarjetasGanador).subscribe();
            }
            // actualiza tarjetas Jugador BD Juego
            this.servicioHttpJuego.actuaLizarBarajaGanadorRonda(idJuego, { idJugador: tablero.ganadorId, tarjetas: tarjetasGanador })
              .subscribe(() => {
                // elimina Tarjetas del tablero
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
      .subscribe()
  }

}
