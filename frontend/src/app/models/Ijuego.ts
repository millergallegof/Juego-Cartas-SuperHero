import { Tarjeta } from './Itarjetas';
import { Jugador } from './Ijugador';
export interface Juego{
    id: string;
    ronda: number;
    mazoJuego: Tarjeta[];
    ganador: string;
    tableroId: string;
    jugadores: Jugador[];
}