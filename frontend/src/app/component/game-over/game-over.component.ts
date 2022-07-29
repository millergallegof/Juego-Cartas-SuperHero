import { Component, OnInit } from '@angular/core';
import { ServiceHttJuego } from 'src/app/service/http-service-juego.service';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';

@Component({
  selector: 'app-game-over',
  templateUrl: './game-over.component.html',
  styleUrls: ['./game-over.component.css']
})
export class GameOverComponent implements OnInit {

  constructor(
    private servicioHttpJuego: ServiceHttJuego,
    private servicioHttpTablero: ServiceHttpTablero,
  ) { }

  ngOnInit(): void {
  }

  salirJuego(): void {
    let { id } = JSON.parse(localStorage.getItem('tablero')!);
    this.servicioHttpTablero.eliminarTablero(id)
      .subscribe(() => {
        localStorage.removeItem("informacionJuego")
        localStorage.removeItem("rolJugador")
        localStorage.removeItem("tablero")
        localStorage.removeItem("limiteRonda")
      })
  }


}
