import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarTarjetasComponentComponent } from './listar-tarjetas-component.component';

describe('ListarTarjetasComponentComponent', () => {
  let component: ListarTarjetasComponentComponent;
  let fixture: ComponentFixture<ListarTarjetasComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarTarjetasComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarTarjetasComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
