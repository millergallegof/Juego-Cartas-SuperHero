import { Component, OnInit } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiting-room-componet',
  templateUrl: './waiting-room-componet.component.html',
  styleUrls: ['./waiting-room-componet.component.css']
})
export class WaitingRoomComponetComponent implements OnInit {


  segundos: number;
  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private router: Router
  ) {
    this.segundos = 3;
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.repatirBaraja();
      this.router.navigate(['juego']);
    }, 3000)
    setInterval(() => this.descontar(), 1000);
  }

  repatirBaraja(): void {
    let idtemJuego = JSON.parse(localStorage.getItem('informacionJuego')!);
    this.servicioHttpJuego
      .repartirBaraja(idtemJuego)
      .subscribe();
  }



  descontar(): void {
    --this.segundos;
  }
}
