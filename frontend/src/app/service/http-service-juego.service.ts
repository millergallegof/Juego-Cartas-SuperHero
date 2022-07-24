import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AngularFirestoreDocument, AngularFirestore } from '@angular/fire/compat/firestore';

import { noop, Observable } from 'rxjs';
import { Tarjeta } from '../models/Itarjetas';
import { PathRest } from '../static/hostbackend';
import { Jugador } from '../models/Ijugador';
import { Juego } from '../models/Ijuego';
import { Bajara } from '../models/Ibaraja';




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
 * @param informacionJuego para crear el campo de juego  
 * @returns el campo de juego creado.
 */
  crearJuego(informacionJuego: Juego): Observable<Juego> {
    return this.httpJuego
      .post<Juego>
      (`${PathRest.getApiJuego}/crear`, informacionJuego, this.httpOptions);
  }

}
