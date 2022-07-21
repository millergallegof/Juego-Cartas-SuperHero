import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Tarjeta } from '../models/Itarjetas';
import { PathRest } from '../static/hostbackend';

@Injectable({
  providedIn: 'root'
})
export class HTTPServiceGameService {

  constructor(private http: HttpClient) { }

  getTarjetas(): Observable<any> {

    return this.http
      .get(`${ PathRest.getApiTarjeta}/listar`);
  }
}
