import { Component, OnInit } from '@angular/core';
import { HTTPServiceGameService } from '../../service/httpservice-game.service';
import { Tarjeta } from '../../models/Itarjetas';

@Component({
  selector: 'app-listar-tarjetas-component',
  templateUrl: './listar-tarjetas-component.component.html',
  styleUrls: ['./listar-tarjetas-component.component.css']
})
export class ListarTarjetasComponentComponent implements OnInit {

  tarjetas: Tarjeta[] = [];

  constructor(public httpService : HTTPServiceGameService) { }

  ngOnInit(): void {

    this.asignarTarjetas();
   }

  asignarTarjetas(){
    this.httpService
    .getTarjetas()
    .subscribe(
      data => {
        this.tarjetas = data
        console.log(data);
        
      },
      error => {console.log(error)});    
    }
}
