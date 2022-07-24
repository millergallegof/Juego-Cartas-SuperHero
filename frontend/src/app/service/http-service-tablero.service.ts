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

  getTablero() {
    
  }

}
