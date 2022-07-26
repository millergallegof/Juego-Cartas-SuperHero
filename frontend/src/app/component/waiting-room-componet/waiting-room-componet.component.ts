import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiting-room-componet',
  templateUrl: './waiting-room-componet.component.html',
  styleUrls: ['./waiting-room-componet.component.css']
})
export class WaitingRoomComponetComponent implements OnInit {
  @Output() comenzarJuego = new EventEmitter<boolean>();
  segundos: number;
  interval: any;
  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private router: Router
  ) {
    this.segundos = 30;
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.repatirBaraja();
      this.router.navigate(['juego']);
      clearInterval(this.interval)
    }, 30000)
    this.interval = setInterval(() => this.descontar(), 1000);
  }

  repatirBaraja(): void {
    let { idJuego } = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego
      .repartirBaraja(idJuego)
      .subscribe(() => {

      });
  }

  descontar(): void {
    --this.segundos;
  }
}
