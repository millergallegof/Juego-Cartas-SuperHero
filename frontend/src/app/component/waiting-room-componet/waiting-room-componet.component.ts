import { Component, OnInit } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiting-room-componet',
  templateUrl: './waiting-room-componet.component.html',
  styleUrls: ['./waiting-room-componet.component.css']
})
export class WaitingRoomComponetComponent implements OnInit {

  minutos: number;
  segundos: number;
  constructor(
    private juegoHttpService:ServiceHttJuego,
    private router :Router
    ) {
    this.minutos = 0;
    this.segundos = 30;
    setInterval(() => this.descontar(), 1000);
  }

  ngOnInit(): void {}

  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos = 30;
      this.router.navigate(['juego']);
      if (--this.minutos < 0) {
        this.minutos = 0;
        this.segundos = 30;
      }
    }
  }
}
