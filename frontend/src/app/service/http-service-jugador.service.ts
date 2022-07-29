import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AngularFirestore, AngularFirestoreDocument } from '@angular/fire/compat/firestore';
import { Observable } from 'rxjs';
import { PathRest } from '../static/hostbackend';
import { Jugador } from '../models/Ijugador';
import { Juego } from '../models/Ijuego';
import { Bajara } from '../models/Ibaraja';
import { Tarjeta } from '../models/Itarjetas';

type DocumentPredicate<T> = string | AngularFirestoreDocument;
@Injectable({
  providedIn: 'root'
})
export class ServiceHttpJugador {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }

  constructor(
    private afs: AngularFirestore,
    private httpJugador: HttpClient,) { }

  /**
   * Metodo encargaado de realizadr el registro de del jugador en MongongoDb
   * @param information del usuario para registrarla en la base de datos
   * @returns la informacionguardada.
   */
  crearJugador(information: Jugador): Observable<Jugador> {
    return this.httpJugador
      .post<Jugador>
      (`${PathRest.getApiJugador}/crear`, information, this.httpOptions);
  }

  actualizarBaraja(idjugador: string, baraja: Bajara): Observable<Jugador> {
    return this.httpJugador
      .post<Jugador>
      (`${PathRest.getApiJugador}/actualizar/baraja/${idjugador}`, baraja);
  }

  apostarTarjeta(idjugador: string, idTarjeta: string): Observable<Jugador> {
    return this.httpJugador
      .post<Jugador>
      (`${PathRest.getApiJugador}/apostarcarta/${idjugador}`, idTarjeta, this.httpOptions)
  }

  cambiarEstado(idjugador: string): Observable<Jugador> {
    return this.httpJugador
      .get<Jugador>
      (`${PathRest.getApiJugador}/cambiarestado/${idjugador}`)
  }

  aumentarPuntos(idjugador: string): Observable<Jugador> {
    return this.httpJugador
      .get<Jugador>
      (`${PathRest.getApiJugador}/aumentarpuntos/${idjugador}`)
  }

  traerTarjetaApostadaJugador(idjugador: string, idTarjeta: string): Observable<Tarjeta> {
    return this.httpJugador
      .post<Tarjeta>
      (`${PathRest.getApiJugador}/obtener/tarjeta/apostada/${idjugador}`, idTarjeta)
  }

  actualizarTarjetasJugador(idJugador:string, tarjetas:Tarjeta[]): Observable<Jugador> {
    return this.httpJugador
    .post<Jugador>
    (`${PathRest.getApiJugador}/actualizar/tarjetas/${idJugador}`, tarjetas)
  }

  //  METODOS FIREBASE TRAER INFORMACION
  /**
   * Metodo encarcado de validar la informacion que pasara en el
   * si la referencia es valida o no.
   */
  private doc<T>(ref: DocumentPredicate<T>): AngularFirestoreDocument {
    return typeof ref === "string" ? this.afs.doc(ref) : ref;
  }
  /**
   * Metodo encargado de hacer el envio de la informacion del documento alojado en FireStore Database
   * Aljado en firebase
   * @param ref recibe el predicado, la ruta y el id del documento a modificar
   * @param data recibe los parametros a modificar en el documento
   * @returns el documento actualizado
   */
  updateInformacion<T>(ref: DocumentPredicate<T>, data: {}) {
    return this.doc(ref).update({
      ...data
    })

  }
}
