import { TestBed } from '@angular/core/testing';

import { ServicioJugadorService } from './servicio-jugador.service';

describe('ServicioJugadorService', () => {
  let service: ServicioJugadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioJugadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
