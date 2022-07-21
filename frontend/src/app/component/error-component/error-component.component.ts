import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-component',
  templateUrl: './error-component.component.html',
  styleUrls: ['./error-component.component.css']
})
export class ErrorComponentComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

}
