import { Component, OnInit } from '@angular/core';
import { AutenticacionServiceService } from '../servicesAuth/autenticacion-service.service';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  constructor(public autenticacionService: AutenticacionServiceService) { }

  ngOnInit(): void {
  }

}
