import { Component, OnInit } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer } from 'rxjs';
import { Bajara } from '../../models/Ibaraja';
import { ServiceHttJuego } from '../../service/http-service-juego.service';

type formatemporal = {
  id?: string;
  [link: string]: any;
  nombre?: string;
  poder?: number;
}

@Component({
  selector: 'app-listar-tarjetas-component',
  templateUrl: './listar-tarjetas-component.component.html',
  styleUrls: ['./listar-tarjetas-component.component.css']
})
export class ListarTarjetasComponentComponent implements OnInit {

  tarjetas: Tarjeta[] = [];
  tarjetImgUser: any[] = [
    '../../../assets/img/icon-user.png'
  ]

  cartasCampo: any[] = [];
  disabledButton: boolean = false;
  informationTarjeta: any[] = [];

  minutos: number;
  segundos: number;
  constructor(
    private servicioHttpJuego: ServiceHttJuego
  ) {
    this.minutos = 0;
    this.segundos = 10;
    setInterval(() => this.descontar(), 1000);
    
  }

  ngOnInit(): void { 
    this.obtenerCartas()
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


  rendirse(): void {
    
  }

  obtenerCartas(): void {
    this.servicioHttpJuego
    .listarJuego()
    .subscribe(data => console.log(data))
  }

  actualizarEstadoCarta(idCarta: string): void {
    this.eliminarCartaMazo(idCarta);
    this.agregarCartaCampo(idCarta);
  }

  eliminarCartaMazo(idCarta: string): void {
    let temporal = this.informationTarjeta.filter(element => element.id !== idCarta);
    this.informationTarjeta = temporal;

  }

  agregarCartaCampo(idCarta: string): void {
    let temporal: formatemporal;
    temporal = this.informationTarjeta.filter(element => element.id == idCarta);
    this.disabledButton = true;
    temporal['link'] = '../../.././assets/img/revezCarta.jpg';
    this.cartasCampo.push(temporal);
  }
}
