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

  enviarTarjeta(idTablero: string, tarjetas: Map<string, Tarjeta>): Observable<Tablero> {
    let tarjetaEnviar = Object.fromEntries(tarjetas);
    console.log(idTablero);
    console.log(tarjetaEnviar);

    return this.httpTablero
      .post<Tablero>
      (`${PathRest.getApiTablero}/recibir/card/${idTablero}`, tarjetaEnviar);
  }

  elegirGanador(idTablero: string): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/ganador/${idTablero}`)
  }

  recibirTarjetas(idTablero: string): Observable<Tarjeta[]> {
    return this.httpTablero
      .get<Tarjeta[]>
      (`${PathRest.getApiTablero}/ganador/tarjetas/${idTablero}`)
  }

  eliminarTarjetas(idTablero: string): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/eliminar/tarjetas/${idTablero}`)
  }

  obtenerTablero(idTablero: string): Observable<Tablero> {
    return this.httpTablero
      .get<Tablero>
      (`${PathRest.getApiTablero}/buscar/${idTablero}`)
  }

}
