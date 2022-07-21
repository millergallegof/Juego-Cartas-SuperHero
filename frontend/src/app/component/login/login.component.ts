import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    public autenticacionService: AutenticacionServiceService,
    public router: Router
    ) { }

  ngOnInit(): void {
  }

}
