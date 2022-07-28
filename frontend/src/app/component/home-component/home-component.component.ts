import { ChangeDetectionStrategy, Component, NgZone, OnInit } from '@angular/core';
import { AutenticacionServiceService } from 'src/app/servicesAuth/autenticacion-service.service';
import { ServiceHttJuego } from '../../service/http-service-juego.service';
import { Tarjeta } from '../../models/Itarjetas';
import { Jugador } from '../../models/Ijugador';
import { ServiceHttpJugador } from '../../service/http-service-jugador.service';
import { Juego } from '../../models/Ijuego';
import { ServiceHttpTablero } from '../../service/http-service-tablero.service';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  jugadores: Jugador[] = [];
  juegos: Juego[] = [];
  idTablero: string = "";
  idJuego: string = "";

  constructor(
    public autenticacionService: AutenticacionServiceService,
    public servicioHttpJugador: ServiceHttpJugador,
    public servicioHttpJuego: ServiceHttJuego,
    public servicioHttpTablero: ServiceHttpTablero,
    private zone: NgZone) {
  }

  ngOnInit(): void {
    this.listarJuegos()
  }

  listarJuegos(): void {
    this.servicioHttpJuego
      .listarJuego()
      .subscribe(data => {
        this.juegos = data.filter(element => element.ganador === "")
      });
  }

  crearSala(nickName: string): void {
    this.crearJugador(nickName);
    this.crearJuego();
    // setTimeout(() => {
    //   this.obtenerCartas();
    // }, 500)
    // this.crearJugador(nickName);
  }

  crearJugador(nickName: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    localStorage.setItem('rolJugador', JSON.stringify("host"))
    this.servicioHttpJugador
      .crearJugador
      ({ id: uid, nickName: nickName, puntos: 0, baraja: null, estado: true })
      .subscribe(data => {
        this.servicioHttpJuego.actualizarJugadores(data.id, data).subscribe(juego => juego)
        this.jugadores.push(data)
      });

    this.servicioHttpJugador.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then((e) => e)
      .catch(err => console.log(err));
  }

  agregarJugador(nickName: string, idJuego: string): void {
    let { uid } = JSON.parse(localStorage.getItem('user')!);
    this.servicioHttpJugador
      .crearJugador
      ({ id: uid, nickName: nickName, puntos: 0, baraja: null, estado: true })
      .subscribe(data => {
        this.servicioHttpJuego.actualizarJugadores(idJuego, data)
          .subscribe(juego => {
            let fechaCreacinJuego = Date.parse(juego.createAt)
            localStorage.setItem('informacionJuego', JSON.stringify({
              idJuego: juego.id,
              ganador: juego.ganador,
              fechaCreacionJuego: Date.parse(new Date().toString()),
              fechaLimite: new Date(fechaCreacinJuego + 60000)
            }))
            localStorage.setItem('rolJugador', JSON.stringify("player"))
            this.servicioHttpTablero.obtenerTablero(juego.tableroId)
              .subscribe(tablero => {
                localStorage.setItem('tablero', JSON.stringify(tablero))
              })
          })
        this.jugadores.push(data)
      });

    this.servicioHttpJugador.updateInformacion(`users/${uid}`, { displayName: nickName })
      .then((e) => e)
      .catch(err => console.log(err));
  }

  crearJuego(): void {
    let tarjetas: Tarjeta[] = [];
    this.servicioHttpJuego
      .crearJuego({ id: null, ronda: 1, mazoJuego: tarjetas, ganador: "", tableroId: "", jugadores: this.jugadores, createAt: "" })
      .subscribe(data => {
        this.crearTablero(data.id!)
        let fechaCreacinJuego = Date.parse(data.createAt)
        localStorage.setItem('informacionJuego', JSON.stringify({
          idJuego: data.id,
          ganador: data.ganador,
          fechaCreacionJuego: fechaCreacinJuego,
          fechaLimite: fechaCreacinJuego + 60000
        }))
        JSON.parse(localStorage.getItem('informacionJuego')!);
      })
  }

  crearTablero(idJuego: string): void {
    this.servicioHttpTablero
      .crearTablero({ id: null, apuesta: Object.create(null), ganadorId: "", tiempo: 30 })
      .subscribe(data => {
        localStorage.setItem('tablero', JSON.stringify(data))
        this.comenzarJuego(idJuego, data.id!)
      });
  }

  comenzarJuego(idJuego: string, idTablero: string): void {
    let tableroJugadores = {
      jugadores: this.jugadores,
      idTablero: idTablero
    }
    this.servicioHttpJuego
      .comenzarJuego(idJuego, tableroJugadores)
      .subscribe(data => {
        this.juegos.push(data)
      });
  }
}
