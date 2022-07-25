import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitingRoomComponetComponent } from './waiting-room-componet.component';

describe('WaitingRoomComponetComponent', () => {
  let component: WaitingRoomComponetComponent;
  let fixture: ComponentFixture<WaitingRoomComponetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WaitingRoomComponetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WaitingRoomComponetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
