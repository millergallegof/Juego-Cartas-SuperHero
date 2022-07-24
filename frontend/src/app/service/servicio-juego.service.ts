import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Juego } from '../models/Ijuego';
import { Observable } from 'rxjs';
import { PathRest } from '../static/hostbackend';

@Injectable({
  providedIn: 'root'
})
export class ServicioJuegoService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }
  constructor(
    private juego: HttpClient,
  ) { }

  /**
   * Metodo encargad de crear el campo de juego para los jugadores
   * @param informacionJuego para crear el campo de juego  
   * @returns el campo de juego creado.
   */
  crearJuego(informacionJuego: Juego): Observable<Juego> {
    return this.juego
      .post<Juego>
      (`${PathRest.getApiJuego}/crear`, informacionJuego, this.httpOptions);
  }
}
