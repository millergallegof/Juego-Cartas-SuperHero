import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';


@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  constructor(
    public autenticacionService: AutenticacionServiceService,
    public router: Router
  ) { }

  ngOnInit(): void {
  }

}
