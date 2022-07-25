import { Component, OnInit } from '@angular/core';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiting-room-componet',
  templateUrl: './waiting-room-componet.component.html',
  styleUrls: ['./waiting-room-componet.component.css']
})
export class WaitingRoomComponetComponent implements OnInit {

 
  segundos: number;
  constructor(
    private router: Router
  ) {
    this.segundos = 10; 
  }

  ngOnInit(): void { 
    setTimeout(() => {
      this.router.navigate(['juego']);
    }, 10000)
    setInterval(() => this.descontar(), 1000);
  }



  descontar(): void {
    --this.segundos;
  }
}
