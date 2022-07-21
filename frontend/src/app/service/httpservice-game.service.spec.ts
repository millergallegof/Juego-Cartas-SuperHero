import { TestBed } from '@angular/core/testing';

import { HTTPServiceGameService } from './httpservice-game.service';

describe('HTTPServiceGameService', () => {
  let service: HTTPServiceGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HTTPServiceGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
