import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AngularFirestoreDocument, AngularFirestore } from '@angular/fire/compat/firestore';

import { noop, Observable } from 'rxjs';
import { Tarjeta } from '../models/Itarjetas';
import { PathRest } from '../static/hostbackend';
import { Jugador } from '../models/Ijugador';
import { Juego } from '../models/Ijuego';
import { Bajara } from '../models/Ibaraja';
import { JugadoresTablero } from '../models/jugadoresTablero';




@Injectable({
  providedIn: 'root'
})
export class HTTPServiceGameService {

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

  comenzarJuego(idJuego: string, infoJuego: JugadoresTablero ): Observable<Juego>{
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/comenzar/${idJuego}`, infoJuego, this.httpOptions);
  }

  repartirBaraja(idJuego: string): Observable<Juego>{
    return this.httpJuego
     .post<Juego>
     (`${PathRest.getApiJuego}/baraja/${idJuego}`, idJuego, this.httpOptions);
  }

  asignarCartasMazo(idJugador: string, ): Observable<Juego>{
    return this.httpJuego
     .post<Juego>
     (`${PathRest.getApiJuego}/baraja/${idJugador}`, idJugador, this.httpOptions);
  }

  listarJuego(): Observable<Juego> {
    return this.httpJuego
      .get<Juego>
      (`${PathRest.getApiTablero}/listar/`)
  }

}
