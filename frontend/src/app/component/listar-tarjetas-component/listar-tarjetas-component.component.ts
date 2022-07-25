import { Component, OnInit } from '@angular/core';
import { Tarjeta } from '../../models/Itarjetas';


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
  // data: any;
  informationTarjeta: any[] = [
    {
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F044.jpg?alt=media&token=fa1ef60e-1a2b-4723-a4e6-5b302f7469e1',
      nombre: "Hombre Lata",
      poder: 120
    },
    {
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F1AE.jpg?alt=media&token=4d9c5620-0899-4658-b868-e4e8a5a47a72',
      nombre: "Tiro Fijo",
      poder: 150
    },
    {
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F063.jpg?alt=media&token=e1356d96-1f63-4186-95ed-6d3348b7fe52',
      nombre: "Hulk vs Hombre Roca",
      poder: 110
    },
    {
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F0E0.jpg?alt=media&token=f55781ae-1c42-48d2-b052-52471dc24b0d',
      nombre: "SpiderMan V.1",
      poder: 115
    },
    {
      link: 'https://firebasestorage.googleapis.com/v0/b/marvel-game-9a665.appspot.com/o/CartasImg%2F168.jpg?alt=media&token=70873d38-e333-4608-8535-b952042c4518',
      nombre: "Mujer Espada",
      poder: 125
    }
  ];


  minutos: number;
  segundos: number;
  constructor() {
    this.minutos = 0;
    this.segundos = 59;
    setInterval(() => this.descontar(), 1000);
  }

  ngOnInit(): void {

    // this.asignarTarjetas();
  }

  descontar(): void {
    if (--this.segundos < 0) {
      this.segundos == 59;
      if (--this.minutos < 0) {
        this.minutos = 0;
        this.segundos = 59;
      }
    }
  }

  // asignarTarjetas(){
  //   this.httpService
  //   .getTarjetas()
  //   .subscribe(
  //     data => {
  //       this.tarjetas = data
  //       console.log(data);

  //     },
  //     error => {console.log(error)});    
  //   }
}
