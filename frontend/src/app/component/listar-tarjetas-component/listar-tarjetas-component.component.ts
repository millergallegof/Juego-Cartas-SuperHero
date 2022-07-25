import { Component, OnInit } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';
import { interval, timer} from 'rxjs';

type formatemporal = {
  id?: string;
  [link:string]: any;
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
  informationTarjeta: any[] = [
    {
      id: "1",
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F044.jpg?alt=media&token=fa1ef60e-1a2b-4723-a4e6-5b302f7469e1',
      nombre: "Hombre Lata",
      poder: 120
    },
    {
      id: "2",
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F1AE.jpg?alt=media&token=4d9c5620-0899-4658-b868-e4e8a5a47a72',
      nombre: "Tiro Fijo",
      poder: 150
    },
    {
      id: "3",
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F063.jpg?alt=media&token=e1356d96-1f63-4186-95ed-6d3348b7fe52',
      nombre: "Hulk vs Hombre Roca",
      poder: 110
    },
    {
      id: "4",
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F0E0.jpg?alt=media&token=f55781ae-1c42-48d2-b052-52471dc24b0d',
      nombre: "SpiderMan V.1",
      poder: 115
    },
    {
      id: "5",
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F168.jpg?alt=media&token=70873d38-e333-4608-8535-b952042c4518',
      nombre: "Mujer Espada",
      poder: 125
    }
  ];


  minutos: number;
  segundos: number;
  constructor() {
    this.minutos = 0;
    this.segundos = 10;
    setInterval(() => this.descontar(), 1000);
  }

  ngOnInit(): void { }

  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos == 10;
      if (--this.minutos < 0) {
        this.minutos = 0;
        this.segundos = 59;
      }
    }
  }

  actualizarEstadoCarta(idCarta: string): void {
    this.eliminarCartaMazo(idCarta);
    this.agregarCartaCampo(idCarta);
  }

  eliminarCartaMazo(idCarta: string): void {
   var temporal = this.informationTarjeta.filter(element => element.id !== idCarta);
   this.informationTarjeta = temporal;

  }

  agregarCartaCampo(idCarta: string): void {
  let temporal: formatemporal;
  temporal = this.informationTarjeta.filter(element => element.id == idCarta);
  this.disabledButton = true;
  temporal['link'] ='../../.././assets/img/revezCarta.jpg';
   this.cartasCampo.push(temporal);

  }
}
