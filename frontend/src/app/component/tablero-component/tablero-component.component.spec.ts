import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableroComponentComponent } from './tablero-component.component';

describe('TableroComponentComponent', () => {
  let component: TableroComponentComponent;
  let fixture: ComponentFixture<TableroComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableroComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableroComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
