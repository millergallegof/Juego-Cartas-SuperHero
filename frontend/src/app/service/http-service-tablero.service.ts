import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Juego } from '../models/Ijuego';
import { Observable } from 'rxjs';
import { PathRest } from '../static/hostbackend';
import { Tarjeta } from '../models/Itarjetas';
import { Tablero } from '../models/Itablero';

@Injectable({
  providedIn: 'root'
})
export class ServiceHttpTablero {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }
  constructor(
    private httpTablero: HttpClient,
  ) { }

  getTableros(): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/listar`)
  }

  crearTablero(tablero: Tablero): Observable<Tablero> {
    return this.httpTablero
      .post<Tablero>
      (`${PathRest.getApiTablero}/crear`, tablero)
  }

  recibirTarjetas(idTablero: string, tarjetas: Map<string, Tarjeta>): Observable<Tablero> {
    return this.httpTablero
      .post<Tablero>
      (`${PathRest.getApiTablero}/recibir/card/${idTablero}`, tarjetas);
  }

  elegirGanador(idTablero: string): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/ganador/${idTablero}`)
  }

  enviarTarjetas(idTablero: string): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/ganador/tarjetas/${idTablero}`)
  }

}
