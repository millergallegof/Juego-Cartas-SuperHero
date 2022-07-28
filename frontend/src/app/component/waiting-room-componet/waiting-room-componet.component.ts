import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';
import { interval, retry, Subscription } from 'rxjs';


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
    private router: Router
  ) { }

  ngOnInit() {
    let { fechaLimiteComenzar } = JSON.parse(localStorage.getItem('informacionJuego')!);
    localStorage.setItem('limiteRonda', JSON.stringify(fechaLimiteComenzar * 60000))
    setTimeout(() => {
      this.fechaFinal = new Date(fechaLimiteComenzar)
      this.subscription = interval(1000)
        .subscribe(x => {
          this.getTimeDifference();
          if (this.secondsToDday <= 0) {
            
            this.ngOnDestroy()
          }
        });
    }, 800)
  }

  ngOnDestroy() {
    let rolJugador = JSON.parse(localStorage.getItem('rolJugador')!);
    if (rolJugador === "host") {
      this.repatirBaraja();
    }
    this.router.navigate(['juego']);
    this.subscription.unsubscribe();
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
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego
      .repartirBaraja(idJuego)
      .subscribe(() => {
      });
  }

}
