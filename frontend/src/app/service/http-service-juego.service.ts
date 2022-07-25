import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { noop, Observable } from 'rxjs';
import { Tarjeta } from '../models/Itarjetas';
import { PathRest } from '../static/hostbackend';
import { Jugador } from '../models/Ijugador';
import { Juego } from '../models/Ijuego';
import { Bajara } from '../models/Ibaraja';
import { JugadoresTablero } from '../models/jugadoresTablero';
import { ElementosJugadorJuego } from '../models/IElementosJugadorJuego';
import { IdentificacionJugador } from '../models/IIdentificacionUsuario';




@Injectable({
  providedIn: 'root'
})
export class ServiceHttJuego {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }
  constructor(
    private httpJuego: HttpClient) { }

  /**
 * Metodo encargad de crear el campo de juego para los jugadores
 * @param juego para crear el campo de juego
 * @returns el campo de juego creado.
 */
  crearJuego(juego: Juego): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/crear`, juego, this.httpOptions);
  }

  comenzarJuego(idJuego: string, infoJuego: JugadoresTablero): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/comenzar/${idJuego}`, infoJuego);
  }

  actualizarJugadores(idJuego: string, jugador: Jugador): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/actualizar/jugadores/${idJuego}`, jugador)
  }

  repartirBaraja(idJuego: string): Observable<Juego> {
    return this.httpJuego
      .get<Juego>
      (`${PathRest.getApiBaraja}/${idJuego}`);
  }

  listarJuego(): Observable<Juego[]> {
    return this.httpJuego
      .get<Juego[]>
      (`${PathRest.getApiJuego}/listar`)
  }

  finalizarJuego(idJuego: string): Observable<Juego> {
    return this.httpJuego
      .get<Juego>
      (`${PathRest.getApiJuego}/finalizar/${idJuego}`)
  }

  asignarGanador(idJuego: string, juego: Juego): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/ganador/${idJuego}`, juego, this.httpOptions)
  }

  aumentaRondaPOSTUseCase(idJuego: string): Observable<Juego> {
    return this.httpJuego
      .get<Juego>
      (`${PathRest.getApiJuego}/rondas/${idJuego}`)
  }

  actuaLizarBarajaGanadorRonda(idJugador: string, elementosJuego: ElementosJugadorJuego): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/ganador/ronda/${idJugador}`, elementosJuego, this.httpOptions)
  }

  asignarCartasMazo(idJugador: string, identificacionJugador: IdentificacionJugador): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/retiro/${idJugador}`, identificacionJugador, this.httpOptions);
  }

}
