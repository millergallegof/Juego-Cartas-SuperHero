import { Component, OnInit } from '@angular/core';
import { ServiceHttJuego } from 'src/app/service/http-service-juego.service';
import { ServiceHttpTablero } from 'src/app/service/http-service-tablero.service';

@Component({
  selector: 'app-ganador',
  templateUrl: './ganador.component.html',
  styleUrls: ['./ganador.component.css']
})
export class GanadorComponent implements OnInit {

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
