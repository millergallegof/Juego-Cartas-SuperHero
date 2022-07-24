import { environment } from '../../environments/environment';

export class PathRest {
    static readonly getApiTarjeta = environment.HostBackend + 'tarjeta';
    static readonly getApiJuego = environment.HostBackend + 'juego';
    static readonly getApiJugador = environment.HostBackend + 'jugador';
    static readonly getApiBaraja = environment.HostBackend + 'baraja';
    static readonly getApiTablero = environment.HostBackend + 'tablero';
}