import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';
import { interval, retry, Subscription } from 'rxjs';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';


@Component({
  selector: 'app-waiting-room-componet',
  templateUrl: './waiting-room-componet.component.html',
  styleUrls: ['./waiting-room-componet.component.css']
})
export class WaitingRoomComponetComponent implements OnInit, OnDestroy {

  private subscription: Subscription;
  timeDifference: number;
  secondsToDday: number;
  minutesToDday: number;

  fechaActual: Date = new Date();
  fechaFinal: Date;
  milliSecondsInASecond: number = 1000;
  minutesInAnHour: number = 60;
  SecondsInAMinute: number = 60;

  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpTablero: ServiceHttpTablero,
    private router: Router
  ) { }

  ngOnInit() {
    setTimeout(() => {
      let { fechaLimiteComenzar } = JSON.parse(localStorage.getItem('informacionJuego')!);
      let rolJugador = JSON.parse(localStorage.getItem('rolJugador')!);
      if (rolJugador === "host") {
        localStorage.setItem('limiteRonda', JSON.stringify(fechaLimiteComenzar + 32000))
      } else {
        localStorage.setItem('limiteRonda', JSON.stringify(fechaLimiteComenzar + 34000))
      }
      this.fechaFinal = new Date(fechaLimiteComenzar)
      this.subscription = interval(1000)
        .subscribe(x => {
          this.getTimeDifference();
          if (this.secondsToDday === 0) {
            this.ngOnDestroy()
          }
        });
    }, 1000)
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    let rolJugador = JSON.parse(localStorage.getItem('rolJugador')!);
    if (rolJugador === "host") {
      this.repatirBaraja();
    }
    this.router.navigate(['juego']);
  }

  private getTimeDifference() {
    this.timeDifference = this.fechaFinal.getTime() - new Date().getTime();
    this.allocateTimeUnits(this.timeDifference);
  }

  private allocateTimeUnits(timeDifference: number) {
    this.secondsToDday = Math.floor((timeDifference) / (this.milliSecondsInASecond) % this.SecondsInAMinute);
    this.minutesToDday = Math.floor((timeDifference) / (this.milliSecondsInASecond * this.minutesInAnHour) % this.SecondsInAMinute);
  }

  repatirBaraja(): void {
    console.log()
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego
      .repartirBaraja(idJuego)
      .subscribe(() => {
      });
  }

  eliminarJuego(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero.eliminarTablero(id).subscribe()
    this.servicioHttpJuego.eliminarJuego(idJuego)
      .subscribe(() => {
        localStorage.removeItem("informacionJuego")
        localStorage.removeItem("rolJugador")
        localStorage.removeItem("tablero")
        localStorage.removeItem("limiteRonda")
      })
  }

}
