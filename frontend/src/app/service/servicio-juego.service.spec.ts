import { TestBed } from '@angular/core/testing';

import { ServicioJuegoService } from './servicio-juego.service';

describe('ServicioJuegoService', () => {
  let service: ServicioJuegoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioJuegoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
