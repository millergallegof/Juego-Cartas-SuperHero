import { Component, Input, OnInit } from '@angular/core';
import { Tarjeta } from 'src/app/models/Itarjetas';

type formatemporal = {
  id?: string;
  [link: string]: any;
  nombre?: string;
  poder?: number;
}

@Component({
  selector: 'app-tablero-component',
  templateUrl: './tablero-component.component.html',
  styleUrls: ['./tablero-component.component.css']
})
export class TableroComponentComponent implements OnInit {
  @Input() tarjeta: Tarjeta;
  minutos: number;
  segundos: number;
  tarjetasTablero: any[] = [];

  constructor() {
    this.minutos = 0;
    this.segundos = 10;
    setInterval(() => this.descontar(), 1000);
  }

  ngOnInit(): void {

  }

  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos = 10;
      if (--this.minutos < 0) {
        this.minutos = 0;
        this.segundos = 59;
      }
    }
  }

  agregarCartaTablero(idTarjeta: string): void {
    let temporal: formatemporal;
    temporal = this.tarjetasTablero.filter(element => element.id == idTarjeta);
    temporal['link'] = '../../.././assets/img/revezCarta.jpg';
    this.tarjetasTablero.push(temporal);
  }


}
