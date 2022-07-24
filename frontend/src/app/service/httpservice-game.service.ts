import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AngularFirestoreDocument, AngularFirestore } from '@angular/fire/compat/firestore';

import { noop, Observable } from 'rxjs';
import { Tarjeta } from '../models/Itarjetas';
import { PathRest } from '../static/hostbackend';
import { Jugador } from '../models/Ijugador';
import { Juego } from '../models/Ijuego';
import { Bajara } from '../models/Ibaraja';


type DocumentPredicate<T> = string | AngularFirestoreDocument;

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
    private afs: AngularFirestore,
    private jugador: HttpClient,
    private juego :HttpClient,
    private tablero: HttpClient) { }


  getTarjetas(): Observable<any> {
    return this.jugador
      .get(`${PathRest.getApiTarjeta}/listar`);
  }

  /**
   * Metodo encargaado de realizadr el registro de del jugador en MongongoDb
   * @param information del usuario para registrarla en la base de datos
   * @returns la informacionguardada.
   */
  crearJugador(information: Jugador): Observable<Jugador> {
    return this.jugador
      .post<Jugador>
      (`${PathRest.getApiJugador}/crear`, information, this.httpOptions);
  }

  /**
   * Metodo encargad de crear el campo de juego para los jugadores
   * @param informacionJuego para crear el campo de juego  
   * @returns el campo de juego creado.
   */
  crearJuego(informacionJuego: Juego): Observable<Juego> {
    return this.jugador
      .post<Juego>
      (`${PathRest.getApiJuego}/crear`, informacionJuego, this.httpOptions);
  }
  /**
   * Metodo encargado de obtener la baraja de la base de datos
   * @returns 
   */
  crearBaraja(): Observable<Bajara> {
    return this.jugador
      .get<Bajara>
      (`${PathRest.getApiBaraja}/crear`);
  }

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
  updateInformacion<T>(ref: DocumentPredicate<T>, data:{}) {
    return this.doc(ref).update({
      ...data
    })
  }
}
