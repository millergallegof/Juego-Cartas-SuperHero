import { Component, Input, OnInit } from '@angular/core';
import { Tarjeta } from 'src/app/models/Itarjetas';

@Component({
  selector: 'app-tablero-component',
  templateUrl: './tablero-component.component.html',
  styleUrls: ['./tablero-component.component.css']
})
export class TableroComponentComponent implements OnInit {
  @Input () tarjeta: Tarjeta;
  minutos: number;
  segundos: number;
  cartasCampo: any[] = [];

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

}
