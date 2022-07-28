import { Tarjeta } from './Itarjetas';
import { Jugador } from './Ijugador';

export interface Juego{
    id: string | null;
    ronda: number;
    mazoJuego: Tarjeta[];
    ganador: string;
    tableroId: string;
    jugadores: Jugador[];
    createAt: string;
}